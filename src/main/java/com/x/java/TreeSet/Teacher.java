package com.x.java.TreeSet;

/**
 * create by 许庆之 on 2020/8/4.
 */
public class Teacher {
    private String name;    // 姓名
    private int age;        // 年龄

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Teacher [name=" + name + ", age=" + age + "]";
    }
}
