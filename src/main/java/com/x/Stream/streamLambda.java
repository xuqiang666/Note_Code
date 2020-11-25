package com.x.Stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: 许庆之 on 2020/11/20.
 */
public class streamLambda {
    public static void main(String[] args) {

        List<User> userList = Arrays.asList(
                new User(1,"拉拉"),
                new User(2,"two"),
                new User(3,"第三人")
        );
        List<Person> personList = Arrays.asList(
                new Person(1,"红嘿嘿"),
                new Person(2,"白拉拉"),
                new Person(3,"灰糊糊")
        );

        /*俩个list 对象根据条件去重
         去掉 userList 中的 User 的 id 不等于 personList 中的 Person 的 id 的对象
         */
        List<User> collect = userList.stream()
                .filter(user -> !personList.stream().map(person -> person.getId()).collect(Collectors.toList())
                        .contains(user.getId())).collect(Collectors.toList());

        /*去重*/
        ArrayList<User> collect1 = userList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(user -> user.getId()))
        ), ArrayList::new));
        //多条件去重
        ArrayList<User> collect2 = userList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(user -> user.getId() + ";" + user.getName()))
        ), ArrayList::new));

        /*简单list集合的求交、去重、差集*/
        List<User> collect3 = collect1.stream().filter(item -> collect2.contains(item)).collect(Collectors.toList());
        List<User> collect4 = collect1.stream().filter(item -> !collect2.contains(item)).collect(Collectors.toList());
        List<User> collect5 = collect1.stream().distinct().collect(Collectors.toList());

        /*排序*/
        List<User> collect6 = collect1.stream().sorted(((o1, o2) -> o1.getId() - o2.getId())).collect(Collectors.toList());
        List<User> collect7 = collect1.stream().sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
        // 多条件组合排序
        List<User> collect8 = collect1.stream().sorted(Comparator.comparing(User::getId).thenComparing(User::getName))
                .collect(Collectors.toList());
        List<User> collect9 = collect1.stream().sorted(Comparator.comparing(User::getId).thenComparing(Comparator.comparing(User::getName).reversed()))
                .collect(Collectors.toList());

        /*过滤*/
        List<User> collect10 = collect1.stream().filter(user -> user.getId() > 2).collect(Collectors.toList());

        /*筛选、转移*/
        List<Integer> collect11 = collect1.stream().map(User::getId).collect(Collectors.toList());
        List<String> 拉拉 = collect1.stream().filter(user -> user.getName().equals("拉拉")).map(User::getName)
                .collect(Collectors.toList());
        // .collect(Collectors.toMap(LogAnalystDTO::getRequestDate, LogAnalystDTO::getRequestDateNum))

        /*统计*/
        int sum = collect1.stream().mapToInt(User::getId).sum();
        Optional<User> max = collect1.stream().max(Comparator.comparing(User::getId));
        int maxId = max.get().getId();
        IntSummaryStatistics intSummary = collect1.stream().collect(Collectors.summarizingInt(User::getId));
        int max1 = intSummary.getMax();

        /*分组*/
        Map<String, List<User>> listMap = collect1.stream().collect(Collectors.groupingBy(User::getName));
        // 多重分组
        Map<Integer, Map<String, List<User>>> mapMap = collect1.stream()
                .collect(Collectors.groupingBy(User::getId, Collectors.groupingBy(User::getName)));
        // 分组并计算综合
        Map<String, Map<Integer, IntSummaryStatistics>> stringMapMap = collect1.stream()
                .collect(Collectors.groupingBy(User::getName, Collectors.groupingBy(User::getId, Collectors.summarizingInt(User::getId))));

        /*遍历*/
        collect1.forEach(user -> System.out.println(user.getName()));

    }
}
