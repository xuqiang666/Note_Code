package com.x.stream;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @Author: 许庆之 on 2020/11/20.
 */
public class User {
    private int id;
    private String name;
    private BigDecimal money;
    private int age;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        //0到n之间的随机int值，包含0而不包含n
        this.age = new Random().nextInt(100);
        this.money = BigDecimal.valueOf(new Random().nextInt(100)).add(new BigDecimal("0.11"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
