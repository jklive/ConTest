package com.gw.ConTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 *
 * 1    高聚低合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 *
 */

public class DoubleThread {

    public static void main(String[] args) {

        Variable variable = new Variable();
        new Thread(() -> {

                for (int i = 1;i <= 10; i++){
                    try {
                    variable.add();

                   }catch (InterruptedException e) {
                     e.printStackTrace();
                    }
                }
        },"AAA").start();
        new Thread(() -> {

            for (int i = 1;i <= 10; i++){
                try {
                    variable.add();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CCC").start();
        new Thread(() -> {

            for (int i = 1;i <= 10; i++){
                try {
                    variable.add();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"EEE").start();

        new Thread(() -> {
            for (int i = 1;i <= 10; i++){
                try {
                    variable.del();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
        new Thread(() -> {
            for (int i = 1;i <= 10; i++){
                try {
                    variable.del();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DDD").start();
        new Thread(() -> {
            for (int i = 1;i <= 10; i++){
                try {
                    variable.del();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"FFF").start();

    }
}

//资源类
//判断，干活，通知
//wait和notify
class Variable {
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();

        try {
//        判断
            while(number != 0){
               condition.await(); //this.wait();
            }
//        干活
            number++;
            System.out.println(Thread.currentThread().getName()+ "\t" +number);

//        通知
            condition.signalAll();//this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void del() throws InterruptedException {

        lock.lock();

        try {
            while (number == 0){
                condition.await();//this.wait();
            }

            number--;
            System.out.println(Thread.currentThread().getName()+ "\t" +number);
            condition.signalAll();//this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
