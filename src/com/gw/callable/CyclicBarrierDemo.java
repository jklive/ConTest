package com.gw.callable;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("集齐七颗龙珠，开始召唤神龙");

        });

        for (int i = 1;i <= 8; i++){

            new Thread(() -> {
                System.out.println("集齐第" + Thread.currentThread().getName() + "颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();

        }


    }
}
