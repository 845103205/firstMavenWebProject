package test.reference;

import java.lang.ref.WeakReference;

public class TestWeakReference {
    public static void main(String[] args) {

        String str=new String("123");
        WeakReference<String> weakReference=new WeakReference<String>(new String("123"));

        System.out.println(weakReference.get());
        System.out.println("______________________________________");
        System.gc();
        if(weakReference.get()==null){
            System.out.println("已经被回收了");
        }else {
            System.out.println(weakReference.get());
        }
    }
}
