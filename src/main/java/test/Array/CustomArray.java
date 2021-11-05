package test.Array;

public class CustomArray<E> {
    private Object[] elementData;
    private Integer size = 0;
    private static  final Integer DEFAULT_CAPACITY = 10;

    public CustomArray(){
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public CustomArray(Integer capacity){
        this.elementData = new Object[capacity];
    }

    public boolean add(E o){
        if(size.equals(elementData.length)){
            System.out.println("扩容:"+size);
            Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData,0,newArray,0,elementData.length);
            elementData=newArray;
        }

        elementData[size++] = o;
        return  true;
    }

    public E get(int index){
        return (E) elementData[index];
    }

    public void set(E e,int index){
        this.elementData[index] = e;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("[");
        for (Integer i = 0; i < this.size; i++) {
           sb.append(this.elementData[i]+",");
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }



    public boolean remove(int index){
        System.arraycopy(this.elementData,index+1,this.elementData,index,this.elementData.length-index-1);
        //将最后一个元素清空，同时也把size减一；
        this.elementData[--size]=null;
        return true;
    }

    public E remove(E element){
        for (int i = 0; i < this.elementData.length; i++) {
            if(this.elementData[i].equals(element)){
                this.remove(i);
                return (E) this.elementData[i];
            }
        }
        return null;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }



    public static void main(String[] args) {
        CustomArray<Integer> customArray = new CustomArray<Integer>(30);
        for (int i = 0; i < 40; i++) {
            customArray.add(i+1);
        }
        Integer a= 3;
        customArray.remove(a);
        System.out.println(customArray);
    }
}