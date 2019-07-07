package com.interview.demo;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
    public static void main(String[] args) {
//      volatile可以保证可见性的验证
//        seeOkByVolatile();

//        volatile不能保证原子性的验证
//        TestAtomic();

    }

    private static void TestAtomic() {
        MyData myData = new MyData();

        for (int i = 1;i <= 20; i++){

              new Thread(() -> {
                  for (int j = 1;j <= 1000; j++){

                      myData.addData();

                  }

              },String.valueOf(i)).start();

        }

        while (Thread.activeCount() > 2){
            Thread.yield();

        }

//        执行结构每次不一致，有遗漏，不能保证原子性
        System.out.println(Thread.currentThread().getName()  + "finally temp value" + myData.temp);
    }


    private static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "come in");
            try { TimeUnit.SECONDS.sleep( 3 ); } catch (InterruptedException e) { e.printStackTrace(); }
            myData.updateTemp();
            System.out.println(Thread.currentThread().getName() + "update to " + myData.temp);

        },"A").start();

//        第二个线程就是main线程
        while(myData.temp == 100){

        }

        System.out.println(Thread.currentThread().getName() + "is over" + myData.temp);
    }

}


class MyData{
    volatile  int temp  = 100;
    public void updateTemp(){
        this.temp = 200;
    }

    public void addData(){ temp++; }
}