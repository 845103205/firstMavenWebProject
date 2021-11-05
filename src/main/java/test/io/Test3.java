package test.io;


import java.io.IOException;
import java.util.*;

public class Test3 {
    public static void main(String[] args) throws IOException {
          /*myArray<String> array =new myArray<String>();
          array.set("1");
          array.set("2");
          array.set("3");
          array.print();*/
        List<Integer> list = new ArrayList();
        List<Integer> list1 = new ArrayList();
        list.add(1);
        list1.add(1);
        list.add(2);
        list1.add(4);
        list.add(3);
        //移除list中list和list1相同的元素
        //list.removeAll(list1);
        //把list1添加到list中
        //list.addAll(list1);

        //list.containsAll(list1);
        //移除list中list和list1中不相同的元素
        //list.retainAll(list1);


        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println(list);
        System.out.println(list1);




    }

}
class  myArray<E> implements List<Integer>{
    private E e;

    private List<E> es;

    public myArray() {
    }

    public  myArray(E e){
     this.e=e;
    }

    public E get(){
        return this.e;
    }

    public void  set(E e){
        if(this.es == null){
            this.es = new ArrayList<>();
        }
        this.e=e;
        this.es.add(e);
    }

    public void print(){

        System.out.println(this.es);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Integer get(int index) {
        return null;
    }

    @Override
    public Integer set(int index, Integer element) {
        return null;
    }

    @Override
    public void add(int index, Integer element) {

    }

    @Override
    public Integer remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return null;
    }
}