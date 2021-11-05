package likou.likou_1114;

public class Likou_1114 {

    private boolean firstFinished;
    private boolean secondFinished;
    private Object lock = new Object();

    public static void main(String[] args) {
        Likou_1114 likou_1114=new Likou_1114();
        int[] array=new int[]{3,2,1};
        for (int i = 0; i < array.length; i++) {
            if(array[i]==1){
                new Thread(new One(likou_1114)).start();
            }else if(array[i]==2){
                new Thread(new Two(likou_1114)).start();
            }else  if(array[i]==3){
                new Thread(new Three(likou_1114)).start();
            }
        }



    }

    public Likou_1114() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized (lock) {
            while (!firstFinished) {
                System.out.println("2");
                lock.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized (lock) {
            while (!secondFinished) {
                System.out.println("3");
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    class LEET1114Print implements Runnable {
        String outPutStr;

        LEET1114Print(String str) {
            outPutStr = str;
        }

        @Override
        public void run() {
            System.out.println(outPutStr);
        }
    }


}
class One implements Runnable  {
    private Likou_1114 likou_1114;

    public One(Likou_1114 likou_1114) {
        this.likou_1114=likou_1114;
    }

    @Override
    public void run() {
        try {
            likou_1114.first(new likou.likou_1114.LEET1114Print("first"));
        } catch (InterruptedException e) {

        }
    }
}

class  Two implements  Runnable{
    private Likou_1114 likou_1114;

    public Two(Likou_1114 likou_1114) {
        this.likou_1114=likou_1114;
    }
    @Override
    public void run() {
        try {
            likou_1114.second(new likou.likou_1114.LEET1114Print("second"));
        } catch (InterruptedException e) {

        }
    }
}

class  Three implements  Runnable{
    private Likou_1114 likou_1114;

    public Three(Likou_1114 likou_1114) {
        this.likou_1114=likou_1114;
    }
    @Override
    public void run() {
        try {
            likou_1114.third(new likou.likou_1114.LEET1114Print("three"));
        } catch (InterruptedException e) {

        }
    }
}