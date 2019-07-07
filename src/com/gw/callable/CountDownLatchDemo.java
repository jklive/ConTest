package com.gw.callable;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

                    public static void main(String[] args) throws InterruptedException {

                        CountDownLatch countDownLatch = new CountDownLatch(10);


                        for (int i = 1;i <= 10; i++){

                            new Thread(() -> {
                                System.out.println("-----1111");
                                System.out.println("-----22222");

                                countDownLatch.countDown();//上面生效，下面不生效

                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(Thread.currentThread().getName() + "位同学离开教室");


                },String.valueOf(i)).start();

            }
            countDownLatch.await();
            System.out.println("班长关门走人");
                        System.out.println(Integer.MAX_VALUE);
                        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
