package com.woniuxy.main.TestStreatm;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class UseStreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        for(int i =0; i<100;i++) {
            list.add((int)(Math.random()*100));
        }
        System.out.println(list);
        // 使用stream流的操作，只输出大于60的数
        //list.stream().filter(i -> i > 60).forEach(System.out::println);
        /**
         * 在Jdk1.8中引入了stream流的概念，这个“流”并不同于IO中的输入和输出流，它是Jdk中的一个类
         * ，具体位置在：java.util.stream.Stream
         * 就是将其他对象（非Stream对象）转为Stream对象。只有两类对象可能转化为Stream对象，分别是：
         *数组（这个数组中的元素必须是引用类型）
         */
        Stream<Integer> stream = list.stream();
        // 转大写
        List<String> strList = new ArrayList();
        strList.add("a");
        strList.add("ab");
        strList.add("abcd");
        strList.add("abcd");
        strList.add("abcd");
        strList.stream().map(s -> s.toUpperCase()).forEach(System.out::println);
        // 过滤集合中字符串长度大于1的数据
        System.out.println("过滤===================");
        strList.stream().filter(value -> value.length() > 1).forEach(System.out::println);

        // 替换元素
        System.out.println("替换===================");
        strList.stream().flatMap(str -> Stream.of("g","sd","dfg","dada")).forEach(System.out::println);
        /**
         * stream.limit(5) //限制，只取前几个元素
         *    .skip(1) //跳过,表示跳过前几个元素
         *    .distinct() //去重
         *    .sorted() //自然排序
         *    .sorted(Integer::compareTo)//自定义排序
          */
        strList.stream().limit(2).forEach(System.out::println);

        strList.stream().distinct().forEach(System.out::println);
    }

}