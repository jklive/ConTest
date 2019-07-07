package com.gw.ConTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();

        //List list = new ArrayList();
        //List list = Collections.synchronizedList(new ArrayList());
        for (int i = 1;i <= 100; i++){

                new Thread(() -> {

                    synchronized (list){

                        list.add(UUID.randomUUID().toString().substring(0,8));
                        if (list.size() != 10){
                            try {
                                list.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        list.notifyAll();
                        System.out.println(list);

                    }
                },String.valueOf(i)).start();

            }




    }
}
