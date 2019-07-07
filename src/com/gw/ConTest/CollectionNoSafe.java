package com.gw.ConTest;

//请举例说明集合是不安全的


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CollectionNoSafe {

    public static void main(String[] args) {
        List list = Collections.synchronizedList(new ArrayList());

        for (int i = 1;i <= 10; i++){

            new Thread(() -> {

                synchronized (list){
                    while (list.size() != 10){
                        list.add(UUID.randomUUID().toString().substring(0,8));
                        try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    }


                list.notifyAll();
                System.out.println(list + Thread.currentThread().getName());
                }

            },String.valueOf(i)).start();

        }
    }

}
