package com.x.java.Entity;

/**
 * Create By  xqz on 2020/8/16.
 */
public class Person {
    String firstname,lastname;
    Boolean sex;
    Integer age;
    public Person(String firstname,String lastname,Boolean sex,Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.age = age;
    }
}
