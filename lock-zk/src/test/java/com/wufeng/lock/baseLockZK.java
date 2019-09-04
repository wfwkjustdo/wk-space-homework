package com.wufeng.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author wangkai
 * @Create 2019/9/4-10:45.
 * @Description
 */
public abstract class baseLockZK implements Lock {
    public static final String CONNECTION_STR = "cos1:2181,cos2:2181,cos3:2181";

    public ZkClient zkClient;

    public baseLockZK() {
        this.zkClient = new ZkClient(CONNECTION_STR,5000);
    }

    @Override
    public void lock() {
        String threadName = Thread.currentThread().getName();
        if (tryLock()){
            System.out.println(threadName+"获取所成功");
        }else{
            System.out.println(threadName+"获取锁失败，进行等待。。。");
            waitLock();

            //递归重新获取锁
            lock();
        }
    }

    /**
     * 等待获取锁
     */
    protected abstract void waitLock();

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 尝试获取锁
     * @return
     */
    @Override
    public abstract boolean tryLock();


    /**
     * 释放锁
     */
    @Override
    public abstract void unlock();


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
