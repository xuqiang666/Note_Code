package com.x.stream;

/**
 * @Author: 许庆之 on 2020/8/26.
 */
public class Student {
    public enum Sax {
        FEMALE, MALE
    }

    private String name;
    private int age;
    private Sax sax;
    private int height;

    public Student(String name, int age, Sax sax, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.sax = sax;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sax getSax() {
        return sax;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sax=" + sax +
                ", height=" + height +
                '}';
    }
}
