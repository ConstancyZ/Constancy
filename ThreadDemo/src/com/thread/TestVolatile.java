package com.thread;

public class TestVolatile {
    private static boolean  igflag = false;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程一正在运行。。。。。。。。。。。。。。。");
                while(!igflag){
                }
                System.out.println("结束。");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程二改变静态变量。。。。。。。。。。。。。。。");
                changeFlag();
            }
        }).start();
    }
     static void changeFlag() {
         System.out.println("改变前准备。。。。。。。。。");
        igflag = true;
         System.out.println("改变后准备。。。。。。。。。");
    }
}
