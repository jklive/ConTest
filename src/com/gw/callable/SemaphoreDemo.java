package com.gw.callable;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//信号灯
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1;i <= 6; i++){

            new Thread(() -> {

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"-抢车位之前--------");
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");

                    try { TimeUnit.SECONDS.sleep( 3 ); } catch (InterruptedException e) { e.printStackTrace(); }

                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }



            },String.valueOf(i)).start();


        }
    }


}
