package com.jm.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterrupteLockDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{

        lock.lock();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========= Try to get lock : " + System.currentTimeMillis());

                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("========= Get lock Time :   " + System.currentTimeMillis());
            }
        });
        System.out.println("========= Begin :           " + System.currentTimeMillis());
        th.start();
        Thread.sleep(2000);
        System.out.println("========= After sleep:      " + System.currentTimeMillis());
        th.interrupt();
        Thread.sleep(2000);
        System.out.println("========= End :             " + System.currentTimeMillis());
    }
}
