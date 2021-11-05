package likou.threadlocaldemo.reptile.demo1;


public  class  Test extends Test2 {
    public static void main(String[] args) {
         int d=0x11;
         int e=017;
        System.out.println(d);
        System.out.println(e);
    }
}

class Test2 {
    private int param3;

    private int param4;

    public Test2() {
        System.out.println("执行了父类的无参构造！！！！！！！！！！");
    }

    public Test2( int param1, int param2) {
        this(1);
        this.param3 = param1;
        this.param4 = param2;
        System.out.println("==============");
    }

    public Test2(int param1) {
        //this(param1, param1);
        this.param3 = param1;
        System.out.println("-----------------------");
        //Test(param1,param2);
    }
}
