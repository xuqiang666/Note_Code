package com.x.java.TreeSet;

import java.util.Set;
import java.util.TreeSet;

/**
 * create by 许庆之 on 2020/8/4.
 */
public class Test01 {
    public static void main(String[] args) {
        Set<Student> set = new TreeSet<>();     // Java 7的钻石语法(构造器后面的尖括号中不需要写类型)
        set.add(new Student("Hao LUO", 33));
        set.add(new Student("XJ WANG", 32));
        set.add(new Student("Bruce LEE", 60));
        set.add(new Student("Bob YANG", 22));

        for(Student stu : set) {
            System.out.println(stu);
        }
        //      输出结果:
        //      Student [name=Bob YANG, age=22]
        //      Student [name=XJ WANG, age=32]
        //      Student [name=Hao LUO, age=33]
        //      Student [name=Bruce LEE, age=60]
    }
}
