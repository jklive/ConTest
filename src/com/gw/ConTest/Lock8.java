package com.gw.ConTest;

import java.util.concurrent.TimeUnit;

/**

 * 笔记
 *  一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *  其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *  锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 *
 *   加个普通方法后发现和同步锁无关
 *  换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 *
 *  都换成静态同步方法后，情况又变化
 *  new  this,具体的一部部手机
 *  静态 class ，唯一的一个模板
 *
 *   所有的非静态同步方法用的都是同一把锁——实例对象本身，
 *
 *  synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 *  具体表现为以下3种形式。
 *  对于普通同步方法，锁是当前实例对象。
 *  对于静态同步方法，锁是当前类的Class对象。
 *  对于同步方法块，锁是Synchonized括号里配置的对象。
 *
 *  当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
 *
 *  也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法
 *  释放锁后才能获取锁，
 *
 *  可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的是不同的锁，
 *  所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 *  所有的静态同步方法用的也是同一把锁——类对象本身，
 *  这两把锁（this/Class）是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 *  但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 *  而不管是同一个实例对象的静态同步方法之间，
 *  还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 *
 *
 *
 *
 *
 *  题目：多线程8锁  默认线程A先执行即先发邮件
 *  1 标准访问，请问先打印邮件还是短信？
 *
 *    ---》Email,如果两个线程A先进入，都加锁，锁的是当前对象this,则一个进来，该对象其他同步方法都只能在外面等候
 *
 *  2 邮件方法暂停4秒钟，请问先打印邮件还是短信？
 *
 *    ---》Email,即便默认邮件先执行，内部睡了4s，另一个同步方法也只能等候该对象执行完毕
 *
 *  3 新增一个普通方法hello()，请问先打印邮件还是hello？
 *
 *   ---》普通方法和同步方法没有关系，两个方法都有机会，如果Email方法内部睡了4s，普通方法就会先执行
 *
 *  4 两部手机，请问先打印邮件还是短信？
 *
 *  ---》两部手机，各执行各的方法，若无延时加载默认Email,会先打Email,否则底层cpu抢占都有机会，
 *       若Email内有延时操作，会先打印SMS
 *
 *  5 两个静态同步方法，同一部手机，请问先打印邮件还是短信？
 *
 *  ---》静态同步方法，锁的是类模板，即.class文件，所以如果默认邮件先发，会先打印Email,加延时也同样先打印Email
 *
 *  6 两个静态同步方法，2部手机，请问先打印邮件还是短信？
 *
 *  ---》同理，两部手机对象为同一个模板，静态同步方法，锁的是类模板，即.class文件，所以如果默认邮件先发，
 *      会先打印Email,加延时也同样先打印Email
 *
 *  7 1个普通同步方法,1个静态同步方法，1部手机，请问先打印邮件还是短信？
 *
 *  ---》普通同步方法锁this,静态方法锁.class，二者不影响，由那个线程先启动，以及方法内是否有延时有关
 *  （只要不影响，方法内延时，也会导致打印顺序改变）
 *
 *  8 1个普通同步方法,1个静态同步方法，2部手机，请问先打印邮件还是短信？
 *
 *  ---》不影响，同上，和时间因素有关
 *
 *  *
 */

//一个普通同步方法，锁的是当前对象this,
//一个静态同步方法，锁的是当前类.class ，二者一个线程互不影响，和普通方法一样，根据线程休眠时间决定
//    两个线程之间

public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程A").start();


        Thread.sleep(400);/*默认了A线程先走*/

        new Thread(() -> {
            try {
                phone.sendSMS();
                //phone.hello();
                //phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程B").start();

    }
}

class Phone {
    public  static  synchronized void sendEmail()throws Exception {
        //try { TimeUnit.SECONDS.sleep( 4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }

    public  synchronized void sendSMS()throws Exception {
        System.out.println("-----sendSMS");
    }

    public void hello() {
        System.out.println("-----hello");
    }
}
