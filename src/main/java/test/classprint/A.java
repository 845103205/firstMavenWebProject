package test.classprint;

public class A {
    static {
        System.out.println("a-static");
    }
    public  A(){
        System.out.println("a-constructor");
    }

    public  A(int a){
        System.out.println("a-constructor-int-a");
    }

    public static void main(String[] args) {
        int b=2;
        int a=b;
        b++;
        System.out.println(a);
    }
}
