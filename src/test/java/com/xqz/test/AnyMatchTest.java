package com.xqz.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Objects;

public class AnyMatchTest {

    public static class Human{
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Human(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        ArrayList<Human> humans = Lists.newArrayList(new Human("aaa"),null);

//        boolean bbb = humans.stream().filter(Objects::nonNull).filter(v -> {
//            return v.getName().equals("bbb");
//        }).anyMatch(v->{
//            return v.getName().equals("bbb");
//        });
//
//        Object[] bbbs = humans.stream().filter(v -> {
//            return v.getName().equals("bbb");
//        }).toArray();

        Object[] objects = humans.stream().map(v -> {
            return v.getName();
        }).toArray();

    }
}
