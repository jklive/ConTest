package com.gw.ConTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

//CollectionNoSafe
public class CollectionTest {

    public static void main(String[] args) {
       // ArrayList arrayList = new ArrayList();

        List arrayList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 1;i <= 10; i++){

            new Thread(() -> {

                synchronized (arrayList){

                    arrayList.add(UUID.randomUUID().toString().substring(0,8));
                    while (arrayList.size() != 10){

                        try {
                            arrayList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    arrayList.notifyAll();

                    System.out.println(arrayList);
                }

            },String.valueOf(i)).start();

        }

    }
}
