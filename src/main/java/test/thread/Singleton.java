package test.thread;



public class Singleton {


    private Singleton() {
        System.out.println("创建了对象");
    }


    public static  class SingletonInstance {
        private static Singleton instance=new Singleton();
    }

    public static  Singleton getInstance(){
        return SingletonInstance.instance;
    }

    public static void main(String[] args) {
       SingtonEnum.SINGTON_ENUM.Single();
    }

}
