package com.x.java.Entity;


import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Create By  xqz on 2020/8/16.
 */
public class Employee implements Comparable<Employee>
{
    private Integer id = -1;
    private Integer age = -1;
    private String firstName = null;

    // 使用 lang3 的CompareToBuilder 进行 组合排序
    @Override
    public int compareTo(Employee o) {
        if (o == null) {
            return -1;
        }
        CompareToBuilder buider = new CompareToBuilder();
        return buider.append(this.getFirstName(),o.getFirstName())
                .append(this.getLastName(),o.getLastName())
                .append(this.getAge(),o.getAge())
                .append(this.getId(),o.getId())
                .toComparison();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName = null;

    public Employee(Integer id, String firstName, String lastName,Integer age) {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
