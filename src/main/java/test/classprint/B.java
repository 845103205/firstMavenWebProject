package test.classprint;


public class B extends A {
    static {
        System.out.println("b-static");
    }
    public B(){

        System.out.println("b-constructor");

    }

    public int B(int a){

        return 1;
    }
}
