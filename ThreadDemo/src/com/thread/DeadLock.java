package com.thread;

/**
 * 死锁
 */
public class DeadLock {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        SynAddRunalbe synAddRunalbe1 = new SynAddRunalbe(obj1, obj2, 1, 2, true);
        Thread thread1 = new Thread(synAddRunalbe1);
        thread1.start();
        SynAddRunalbe synAddRunalbe2 = new SynAddRunalbe(obj1, obj2, 1, 2, false);
        Thread thread2 = new Thread(synAddRunalbe2);
        thread2.start();
    }
    public static class SynAddRunalbe implements Runnable{
        Object obj1;
        Object  obj2;
        int a,b;
        boolean flag;
        public  SynAddRunalbe(Object obj1, Object obj2, int a, int b, boolean flag){
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.a = a;
        this.b = b;
        this.flag = flag;
        }
        @Override
        public void run() {
            try {
                if (flag) {
                    synchronized (obj1){
                        Thread.sleep(1000);
                        synchronized (obj2) {
                            System.out.println(a+b);
                        }
                    }
                } else {
                    synchronized (obj2){
                        Thread.sleep(1000);
                        synchronized (obj1) {
                            System.out.println(a+b);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
