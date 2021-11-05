package test.reference.softreference;

import javax.lang.model.type.ReferenceType;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class Test {
    public static void main(String[] args){
        System.out.println("开始");
        A a = new A();
        SoftReference<A> sr = new SoftReference<A>(a);
        System.gc();
        a = null;
        if(sr!=null){
            a = sr.get();
            System.out.println(1);
        }
        else{
            System.out.println(2);
            a = new A();
            sr = new SoftReference<A>(a);
        }
        System.out.println("结束");
    }

}

class A{
    int[] a ;
    public A(){
        a = new int[1024*1024*1];
    }
}
