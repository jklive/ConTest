package com.gw.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//第三种多线程方式Callable
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Tacket tacket = new Tacket();

        FutureTask futureTask = new FutureTask(() -> {
            for (int i = 1;i <= 40; i++){

                tacket.saleTackets();

            }
        },"okA");
        FutureTask futureTask2 = new FutureTask(() -> {
            for (int i = 1;i <= 15; i++){

                tacket.saleTackets();

            }
        },"okB");

        new Thread(futureTask,"线程A").start();

        System.out.println(futureTask.get());
        new Thread(futureTask2,"线程B").start();
        //System.out.println(futureTask2.get());

    }






}

//资源类
class Tacket {

    private int tackets = 30;
    Lock lock = new ReentrantLock();//同步锁

    public  void saleTackets(){


        lock.lock();
        try {
            if (tackets > 0) {
                System.out.println(Thread.currentThread().getName() +"卖出第" + (tackets--) + "张票，剩余票数为" + tackets);
            }
        } finally {
            lock.unlock();//关闭锁

        }
    }

}
