package com.x.stream.comparator;

import com.google.common.collect.ComparisonChain;
import com.x.java.Entity.Employee;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.Test;

import java.util.*;

/**
 * Create By  xqz on 2020/8/15.
 * 根据map的value进行排序
 * reverse() 方法的实现 ：
 * 如果传入的不是一个比较器，那么就返回一个逆序比较器 ReverseComparator.REVERSE_ORDER = Collections的内部静态类 ReverseComparator ，此类的compare方法 return c2.compareTo(c1);
 * 否则返回 ReverseComparator2（Collections的内部静态类），此类的compare方法 return cmp.compare(t2, t1);
 */
public class SortByValue {
    public static void main(String[] args) {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("a", "aaaa");
        map.put("c", "cccc");
        map.put("b", "bbbb");
        map.put("d", "dddd");

        ArrayList<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }.reversed());
        for (Map.Entry<String, String> e : list) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    /**
     * 使用 commonns的CompareToBuilder组合排序 ，传入比较器
     */
    @Test
    public void test01() {
        List<Employee> list = Arrays.asList(new Employee(1, "A", "B", 34),
                new Employee(4, "C", "D", 30),
                new Employee(3, "B", "A", 31),
                new Employee(2, "D", "C", 25));

        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return new CompareToBuilder()
                        .append(o1.getFirstName(), o2.getFirstName())
                        .append(o1.getLastName(), o2.getLastName())
                        .append(o1.getAge(), o2.getAge())
                        .toComparison();
            }
        });
        System.out.println(list);
    }

    /**
     * 使用 guava的ComparisonChain比较器链组合排序
     */
    @Test
    public void test03() {
        List<Employee> list = Arrays.asList(new Employee(1, "A", "B", 34),
                new Employee(4, "C", "D", 30),
                new Employee(3, "B", "A", 31),
                new Employee(2, "D", "C", 25));

        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee empOne, Employee empTwo) {
                return ComparisonChain.start()
                        .compare(empOne.getFirstName(), empTwo.getFirstName())
                        .compare(empOne.getLastName(), empTwo.getLastName())
                        .compare(empOne.getAge(), empTwo.getAge())
                        .result();
            }
        });

        System.out.println(list);
    }
}