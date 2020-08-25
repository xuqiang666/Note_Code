package com.x.java.Comparator;

import com.x.java.Entity.Human;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Create By  xqz on 2020/8/16.
 */
public class useLambda {

    public static void main(String[] args) {
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Sarah", 10));
        humans.add(new Human("Jack", 12));

        humans.sort((Human h1, Human h2) -> h1.getName().compareTo(h2.getName()));
        //humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));//简化
    }

    /** 增强版的Comparator接口 ； reversed() 逆序（comparator中的default实现） */
    @Test
    public void test01(){
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Sarah", 10));
        humans.add(new Human("Jack", 12));

        Collections.sort(humans, Comparator.comparing((Human human) -> human.getName()).reversed());
    }

    /** 组合排序 */
    @Test
    public void test02(){
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Sarah", 10));
        humans.add(new Human("Jack", 12));
        humans.add(new Human("Jack", 10));

        Collections.sort(humans,Comparator.comparing(Human::getName).thenComparing(Human::getAge));
    }
}
