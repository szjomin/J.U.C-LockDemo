package com.jm.lock;

import org.apache.tomcat.jni.Time;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{

        lock.lock();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("========= Try to get lock : " + System.currentTimeMillis());
                boolean get = false;
                try {
                    get = lock.tryLock(3000, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("========= Get lock :        " + get );
                System.out.println("========= Get lock Time :    " + System.currentTimeMillis());


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
