package test.threadlocal;

public class Base {
    protected static ThreadLocal<Long> threadLocal=new ThreadLocal<Long>(){
        //延迟加载
        @Override
        protected  Long  initialValue(){
            System.out.println("init..");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        //set了值就不会去执行initialvalue方法了。哈哈哈哈
        threadLocal.set(80l);
        //移除的话又会去执行initiavalue方法了。哈哈哈哈
        threadLocal.remove();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //threadLocal.set(81l);
                System.out.println(threadLocal.get());
            }
        }).start();
        System.out.println(threadLocal.get()+"--main");
        System.out.println(threadLocal.get()+"--main");
    }
}
