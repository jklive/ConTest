package com.gw.ConTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：三个售票员  卖出  30张票
 *
 *	多线程编程的企业级套路+模板
 *
 * 1    在高内聚低耦合的前提下，线程    操作(对外暴露的调用方法)    资源类
 */

public class SaleTacketTest {
    public static void main(String[] args) {

        Tacket tacket = new Tacket();

       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });*/
        new Thread(() -> {
            for (int i = 1;i <= 31; i++) {
                tacket.saleTackets();

            } },"窗口一").start();
        new Thread(() -> { for (int i = 1;i <= 31; i++) tacket.saleTackets(); },"窗口二").start();
        new Thread(() -> { for (int i = 1;i <= 31; i++) tacket.saleTackets(); },"窗口三").start();



    }
}

//资源类-->对外暴露调用方法
class Tacket{

    private int tackets = 30;

    public  void saleTackets(){
        Lock lock = new ReentrantLock();//同步锁

        lock.lock();
        try {
            if (tackets > 0) {
                System.out.println("卖出第" + (tackets--) + ",张票，剩余票数为" + tackets);
            }
        } finally {
            lock.unlock();//关闭锁

        }
    }

}
