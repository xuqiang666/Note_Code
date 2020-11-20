package com.x.java.Stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author 许庆之 on 2020/8/25.
 * java8 快速实现List转map 、分组、过滤等操作
 */
public class ATest {
    @Test
    public void test01(){
        //存放apple对象集合
        List<Apple> appleList = new ArrayList<>();

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        /* 分组
        List 以ID分组 Map<Integer,List<Apple>>
         */
        Map<Integer, List<Apple>> groupBy = appleList.stream()
                .collect(Collectors.groupingBy(Apple::getId));
        System.err.println("groupBy: "+groupBy);

        /*
          List -> Map
          需要注意的是：
          toMap 如果集合对象有重复的key，会报错Duplicate key ....
           apple1,apple12的id都为1。
           可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream()
                .collect(Collectors.toMap(Apple::getId, a -> a,(k1,k2)->k1));
        System.out.println("toMap: "+appleMap);


        //从集合中过滤出符合条件的数据
        List<Apple> filterList = appleList.stream()
                .filter(a -> "香蕉".equals(a.getName())).collect(Collectors.toList());
        System.err.println("filterList:"+filterList);


        //求和  将集合按照某个属性求和  ：计算 总金额
        BigDecimal totalMoney = appleList.stream()
                .map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:"+totalMoney);
        //totalMoney:17.48



        // 根据id去重
        List<Apple> unique = appleList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingLong(Apple::getId))), ArrayList::new)
        );
        System.out.println("unique: "+unique);

    }

}
