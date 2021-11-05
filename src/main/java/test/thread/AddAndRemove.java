package test.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AddAndRemove {

    public static void main(String[] args) {
        AtomicInteger  atomicInteger=new AtomicInteger(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndIncrement();
                System.out.println(andIncrement);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndIncrement();
                System.out.println(andIncrement);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndDecrement();
                System.out.println(andIncrement);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndDecrement();
                System.out.println(andIncrement);
            }
        }).start();


       /*final Integer atomicInteger=1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger++;
                System.out.println(andIncrement);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndIncrement();
                System.out.println(andIncrement);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndDecrement();
                System.out.println(andIncrement);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andIncrement = atomicInteger.getAndDecrement();
                System.out.println(andIncrement);
            }
        }).start();*/
    }
}
