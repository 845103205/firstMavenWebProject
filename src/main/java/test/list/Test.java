package test.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        //删除list中的元素
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if(next != null && next.equals(2)){
                iterator.remove();
            }
        }

        System.out.println(list.toString());
    }

}
