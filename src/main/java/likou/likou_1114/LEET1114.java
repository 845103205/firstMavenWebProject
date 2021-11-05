package likou.likou_1114;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class LEET1114 {
    public static void main(String[] args) {
        int[] order = {3, 1, 2};
        LEET1114 leet1114 = new LEET1114();
        for (int num : order) {
            if (num == 1) {
                new Thread(new LEET1114Thread1(leet1114)).start();
            } else if (num == 2) {
                new Thread(new LEET1114Thread2(leet1114)).start();
            } else {
                new Thread(new LEET1114Thread3(leet1114)).start();
            }
        }
    }

    // 方法1：使用信号量
    public Semaphore seam_first_two = new Semaphore(0);

    public Semaphore seam_two_second = new Semaphore(0);

    public LEET1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        seam_first_two.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        seam_first_two.acquire();//第二个方法依赖第一个方法的执行结果
        printSecond.run();
        seam_two_second.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        seam_two_second.acquire();//第三个方法依赖第二个方法的执行结果。
        printThird.run();
    }

    // 方法2：模拟CAS自旋
    //该方法通过cpu一直占用的方式进行，定义一个volatile关键字修饰的变量，当三个线程运行的时候，第三个线程先到但是由于task=1，
    //所以该线程不会执行，当第一个线程启动运行的时候，task值正好判断为真，就会执行first方法里面的内容，而其他运行的线程没有
    //被挂起或者睡眠只是一直占用cpu，当第一个线程执行完毕后，就会去改变task值，同时第二个线程一直在自旋，当发现task值变为2的时候
    //就会去执行该方法的内容
/*    private volatile int task = 1;

    public LEET1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        while (true) {
            System.out.println("first try...");
            if (task == 1) {
                printFirst.run();
                task = 2;
                break;
            }
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (true) {
            System.out.println("second try...");
            if (task == 2) {
                printSecond.run();
                task = 3;
                break;
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (true) {
            System.out.println("third try...");
            if (task == 3) {
                printThird.run();
                break;
            }
        }
    }*/
/*    //该线程同步方式为计数同步，通过构造方法传入一个数字，通过调用await方法挂起线程，通过countdown方法让数字减一，
    // 当该数字为0的时候，让挂起的线程立即运行。
    private CountDownLatch countDownLatch1 = new CountDownLatch(1);
    private CountDownLatch countDownLatch2 = new CountDownLatch(1);

    public void first(Runnable printFirst) {
        printFirst.run();
        System.out.println("1--1");
        countDownLatch1.countDown();
        System.out.println("1---2");
    }

    public void second(Runnable printSecond) throws InterruptedException {
        System.out.println("2--1");
        countDownLatch1.await();
        System.out.println("2---2");
        printSecond.run();
        countDownLatch2.countDown();
        System.out.println("2--3");
    }

    public void third(Runnable printThird) throws InterruptedException {
        System.out.println("3---1");
        countDownLatch2.await();
        printThird.run();
        System.out.println("3---2");
    }*/
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

class LEET1114Thread1 implements Runnable {
    private LEET1114 leet1114;

    LEET1114Thread1(LEET1114 leet1114) {
        this.leet1114 = leet1114;
    }

    @Override
    public synchronized void run() {
        try {
            leet1114.first(new LEET1114Print("first"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LEET1114Thread2 implements Runnable {
    private LEET1114 leet1114;

    LEET1114Thread2(LEET1114 leet1114) {
        this.leet1114 = leet1114;
    }

    @Override
    public synchronized void run() {
        try {
            leet1114.second(new LEET1114Print("second"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LEET1114Thread3 implements Runnable {
    private LEET1114 leet1114;

    LEET1114Thread3(LEET1114 leet1114) {
        this.leet1114 = leet1114;
    }

    @Override
    public synchronized void run() {
        try {
            leet1114.third(new LEET1114Print("third"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
