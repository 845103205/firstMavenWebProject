package likou.likou_1114;

class Foo {

    /*private  boolean firstFinished;
    private  boolean secondFinished;
    private  Object lock = new Object();*/

     private boolean firstFinished;
     private boolean secondFinished;
     private Object Lock=new Object();

    public Foo() {

    }

    public  void first(Runnable printFirst) throws InterruptedException {

       /* synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }*/

       synchronized (Lock){
           printFirst.run();
           firstFinished=true;
           Lock.notifyAll();
       }
    }

    public  void second(Runnable printSecond) throws InterruptedException {

        /*synchronized (lock) {
            while (!firstFinished) { //第二块核心代码的执行依赖第一块代码的执行结果
                lock.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }*/


        synchronized(Lock){
            if(!firstFinished){
                Lock.wait();
            }
            printSecond.run();
            secondFinished=true;
            Lock.notifyAll();
        }
    }

    public  void third(Runnable printThird) throws InterruptedException {

        /*synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }*/


        synchronized(Lock){
            if(!secondFinished){
                Lock.wait();
            }
            printThird.run();

        }
    }

    public static void main(String[] args) throws Exception {
        Foo foo=new Foo();
        Thread thread=new Thread();
        foo.second(new Two());

       //Thread.sleep(100);
        foo. first(new One());
       // Thread.sleep(100);
        foo. third(new Three());
    }
    static class One implements Runnable{

        @Override
        public void run() {
            System.out.println("one");
        }
    }

    static class Two implements Runnable{

        @Override
        public void run() {
            System.out.println("two");
        }
    }

    static class Three implements Runnable{

        @Override
        public void run() {
            System.out.println("three");
        }
    }
}
