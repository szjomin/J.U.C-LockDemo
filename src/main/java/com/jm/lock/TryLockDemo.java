package com.jm.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {

        lock.lock();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========= Try to get lock : " + System.currentTimeMillis());
                boolean get = lock.tryLock();
                //这里将显示失败， 因为主线程没有释放锁，所以这个线程获取不到
                System.out.println("========= Get lock :        " + get);
                System.out.println("======== Get lock Time :    " + System.currentTimeMillis());


            }
        });

        System.out.println("========= Begin :           " + System.currentTimeMillis());
        th.start();
        Thread.sleep(2000);
        System.out.println("========= After sleep:      " + System.currentTimeMillis());
        lock.unlock();
        System.out.println("========= End :             " + System.currentTimeMillis());

    }
}
