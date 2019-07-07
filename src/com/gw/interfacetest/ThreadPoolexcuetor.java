package com.gw.interfacetest;



class ThreadLock implements  Runnable{
    private String lockA;
    private  String lockB;

    public ThreadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (this.lockA){
            System.out.println(Thread.currentThread().getName()+"获取"+lockA+"尝试获取"+lockB);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.lockB){
                System.out.println(Thread.currentThread().getName()+"获取"+lockB+"尝试获取"+lockA);
            }

        }

    }
}



public class ThreadPoolexcuetor {
    public static void main(String[] args) {

        String lockA ="lockA";
        String lockB ="lockB";
        String lockC ="lockC";
        new Thread(new ThreadLock(lockA,lockB),"AAA").start();
        new Thread(new ThreadLock(lockB,lockA),"BBB").start();

    }
}
