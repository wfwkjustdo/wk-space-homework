package com.wufeng.lock;

import java.util.concurrent.locks.Lock;

/**
 * @Author wangkai
 * @Create 2019/9/4-11:36.
 * @Description
 */
public class ZKLockTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++){

            new Thread(()->{
                Lock zkLock = new LockZK();
                System.out.println(Thread.currentThread().getName() + "-> 尝试获取锁");
                zkLock.lock();

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkLock.unlock();
            }).start();

        }

    }
}
