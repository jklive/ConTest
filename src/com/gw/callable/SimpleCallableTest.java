package com.gw.callable;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCallableTest {

    public static void main(String[] args) {

        SaleTackets saleTackets = new SaleTackets();

        FutureTask futureTask1 = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 1;i <= 10; i++){

                    saleTackets.saleTacket();

                }
                return "窗口卖票完成";
            }
        });
        FutureTask futureTask2 = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 1;i <= 10; i++){

                    saleTackets.saleTacket();

                }
                return "窗口卖票完成";
            }
        });
        FutureTask futureTask3 = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                for (int i = 1;i <= 10; i++){

                    saleTackets.saleTacket();

                }
                return "窗口卖票完成";
            }
        });

        new Thread(futureTask1,"A").start();
        new Thread(futureTask2,"B").start();
        new Thread(futureTask3,"C").start();

    }

}

class SaleTackets  {
    private int tackets = 30;

    Lock lock = new ReentrantLock();

    public void saleTacket(){
        lock.lock();
        try {
            if (tackets > 0){
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (tackets)-- + ",还剩" + tackets);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

