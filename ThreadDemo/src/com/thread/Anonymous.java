package com.thread;

/**
 * 匿名内部类
 */
public class Anonymous {
}
/*abstract class Person {
    public abstract void eat();
}

class Child extends Person {
    @Override
    public void eat() {
        System.out.println("eat something");
    }
}

 class Demo {
    public static void main(String[] args) {
        Person p = new Child();
        p.eat();
    }
}*/
/*
可以看到，我们用Child继承了Person类，然后实现了Child的一个实例，将其向上转型为Person类的引用

但是，如果此处的Child类只使用一次，那么将其编写为独立的一个类岂不是很麻烦？

这个时候就引入了匿名内部类

基本实现
 */
/*abstract class Person {
    public abstract void eat();
}*/
/*
可以看到，我们直接将抽象类Person中的方法在大括号中实现了
这样便可以省略一个类的书写
并且，匿名内部类还能用于接口上
 */
/*
class Demo {
    public static void main(String[] args) {
        Person p = new Person() {
            @Override
            public void eat() {
                System.out.println("eat something");
            }
        };
        p.eat();
    }
}*/
// 接口上
interface Person {
    public void eat();
}
class Demo {
    public static void main(String[] args) {
        Person p = new Person() {
            @Override
            public void eat() {
                System.out.println("eat something");
            }
        };
        p.eat();
    }
}