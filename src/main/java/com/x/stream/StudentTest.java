package com.x.stream;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * @Author: 许庆之 on 2020/8/26.
 */
public class StudentTest {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Fndroid", 22, Student.Sax.MALE, 180),
                new Student("Jack", 20, Student.Sax.MALE, 170),
                new Student("Liliy", 18, Student.Sax.FEMALE, 160)
        );

        // *输出所有性别为MALE 的学生
        students.stream().filter(student -> student.getSax() == Student.Sax.MALE)
                .forEach(System.out::println);

        // *求所有学生的平均年龄
        OptionalDouble averageAge = students.stream().mapToInt(Student::getAge)
                .average();
        //使用OptionalDouble减少空指针异常，可通过orElse获取值对空置0
        System.out.println("所有学生的平均年龄为：" + averageAge.orElse(0));

        // *输出每个学生姓名的大写形式
        List<String> nameList = students.stream().map(Student::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("学生姓名：" + nameList);

        // *按照年龄从小到大排序
        List<Student> ageList = students.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .collect(Collectors.toList());
        System.out.println("年龄排序：" + ageList);

        // *判断是否存在某个学生
        boolean isContain = students.stream()
                .anyMatch(student -> student.getName().equals("Fndroid"));
        System.out.println("是否存在学生Fndroid：" + isContain);

        // *将所有学生按性别分组
        Map<Student.Sax, List<Student>> saxListMap = students.stream()
                .collect(Collectors.groupingBy(Student::getSax));
        System.out.println("学生性别分组: " + saxListMap.get(Student.Sax.MALE));

        // *求每个学生身高比例
        double sum = students.stream().mapToInt(Student::getHeight).sum();
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        List<String> percentAgeList = students.stream()
                .mapToInt(Student::getHeight)
                .mapToDouble(value -> value / sum * 100)
                .mapToObj(per -> decimalFormat.format(per) + "%")
                .collect(Collectors.toList());
        System.out.println("每个学生比例为：" + percentAgeList);

    }
}
