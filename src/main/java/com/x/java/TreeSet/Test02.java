package com.x.java.TreeSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * create by 许庆之 on 2020/8/4.
 */
public class Test02 {
    public static void main(String[] args) {
        List<Teacher> list = new ArrayList<>();     // Java 7的钻石语法(构造器后面的尖括号中不需要写类型)
        list.add(new Teacher("as",23));
        list.add(new Teacher("Hao LUO", 33));
        list.add(new Teacher("XJ WANG", 32));
        list.add(new Teacher("Bruce LEE", 60));
        list.add(new Teacher("Bob YANG", 22));

        // 通过sort方法的第二个参数传入一个Comparator接口对象
        // 相当于是传入一个比较对象大小的算法到sort方法中
        // 由于Java中没有函数指针、仿函数、委托这样的概念
        // 因此要将一个算法传入一个方法中唯一的选择就是通过接口回调
        Collections.sort(list, new Comparator<Teacher>() {

            @Override
            public int compare(Teacher o1, Teacher o2) {
                return o1.getName().compareTo(o2.getName());    // 比较学生姓名
            }
        });

        for(Teacher teacher : list) {
            System.out.println(teacher);
        }
        //      输出结果:
        //      Teacher [name=Bob YANG, age=22]
        //      Teacher [name=Bruce LEE, age=60]
        //      Teacher [name=Hao LUO, age=33]
        //      Teacher [name=XJ WANG, age=32]
    }
}

