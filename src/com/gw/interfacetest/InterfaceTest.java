package com.gw.interfacetest;


import java.util.concurrent.Executor;
import java.util.function.Consumer;

//四大接口
public class InterfaceTest {

    public static void main(String[] args) {

        /*Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };*/
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("lalala");


    }
}

interface  Myinterface{
//    消费型接口
    public void MyConsumer(String s);

}
