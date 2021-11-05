package test.Array;

public class CustomLinkList {
    /*链表中第一个节点*/
    private Node first;
    /*链表中最后一个节点*/
    private Node last;

    private int size;

    public boolean add(Object element){
        Node node = new Node(element);

        if(first == null){
            first = node;
            last = node;
        }else {
            /*添加的节点的前节点指向当前链表的最后一个节点*/
            node.setPrevious(last);
            /*添加节点的后节点指向空*/
            node.setNext(null);
            /*让上一个节点的后节点指向当前节点*/
            last.setNext(node);

            last = node;
        }
        size++;
     return  true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Node temp = first;

        while (temp != null){
            sb.append(temp.getElement()+",");
            temp = temp.getNext();
        }
        sb.setCharAt(sb.length()-1,']');

       return  sb.toString();
    }

    public Object get(int index){
        if(index<0 || index>size-1){
            throw new RuntimeException("索引不合法");
        }

        Node temp = null;
        //二分法查找
        if(index<(size>>1)){
            temp = first;

            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
        }else {
            temp = last;
            for (int i = size-1; i > index; i--) {
                temp = temp.getPrevious();
            }
        }


        return  temp.getElement();
    }

    public void remove(int index){
        Node temp = getNode(index);

        if(temp != null ){
            Node up = temp.getPrevious();
            Node down = temp.getNext();
            if(up != null){
                up.setNext(down);
            }

            if(down != null){
                down.setPrevious(up);
            }

            if(index == 0){
                first = down;
            }

            if(index == size-1){
                last = up;
            }

            size--;
        }
    }

    public Node getNode(int index){
        if(index<0 || index>size-1){
            throw new RuntimeException("索引不合法");
        }

        Node temp = null;
        //二分法查找
        if(index<(size>>1)){
            temp = first;

            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
        }else {
            temp = last;
            for (int i = size-1; i > index; i--) {
                temp = temp.getPrevious();
            }
        }

        return  temp;
    }

    public void add(int index,Object obj){
        Node newNode = new Node(obj);
        Node temp = getNode(index);

        if(temp != null){
            Node up = temp.getPrevious();
            up.setNext(newNode);
            newNode.setPrevious(up);

            newNode.setNext(temp);
            temp.setPrevious(newNode);
            size++;
        }
    }

    public static void main(String[] args) {
        CustomLinkList customLinkList = new CustomLinkList();
        customLinkList.add("a");
        customLinkList.add("b");
        customLinkList.add("c");
        customLinkList.add("d");

        customLinkList.add(1,"f");
        System.out.println(customLinkList);
    }
}
