package test.map;


import java.util.HashMap;

public class CustomHashMap {
    private Node2[] table; //位桶数组
    private  int size;
    public CustomHashMap() {
        table = new Node2[16];//长度一般为2的整数次幂

    }

    public  void put(Object key,Object value){
        Node2 newNode = new Node2();
        newNode.hash = myHash(key.hashCode(),table.length);
        newNode.key = key;
        newNode.value = value;
        newNode.next = null;

        Node2 temp =table[newNode.hash];

        Node2 iteLast = null;//正在遍历的最后一个元素

        boolean keyRepeat = false;
        if(temp == null){
            //新节点放入hashmap的数组中
            table[newNode.hash] = newNode;
            size++;
        }else {
            while (temp != null){
                //判断key如果重复，则覆盖。
                if(temp.key.equals(key)){
                  //改变value值相同就可以了
                  temp.value = value;
                  keyRepeat = true;
                  break;
                }else {
                    //key不重复,则遍历下一个
                    iteLast = temp;
                    temp = temp.next;
                }
            }
            if(!keyRepeat){
                iteLast.next = newNode;
                size++;
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < table.length; i++) {
            Node2 temp = table[i];
            while (temp != null){
                sb.append(temp.key+":"+temp.value+",");
                temp = temp.next;
            }
        }
        sb.setCharAt(sb.length()-1,'}');
        return  sb.toString();
    }

    public Object get(Object key){
        int hash = myHash(key.hashCode(), table.length);
        Object value = null;
        if(table[hash] != null){
            Node2 temp = table[hash];
            while (temp!= null){
                if(temp.key.equals(key)){
                    value = temp.value;
                    break;
                }else {
                    temp = temp.next;
                }
            }
        }
        return  value;
    }

    public static int myHash(int v , int length){
        return  v&(length-1);
    }

    public static void main(String[] args) {
        CustomHashMap customHashMap = new CustomHashMap();
        customHashMap.put("a","a");
        customHashMap.put("b","b");
        customHashMap.put("c","c");
        customHashMap.put("c","c");
        customHashMap.put(53,"e");
        customHashMap.put(69,"e");

        Object o = customHashMap.get("c");
        System.out.println(o);

    }
}
