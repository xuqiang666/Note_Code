package com.x.stream;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MapReduceTEst {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("我阿达发 ", "答复啊", "而非微软给", "而我认为");
        String s = list.stream().map(a -> a + "\n")
                .reduce((a, b) -> a + "" + b)
                .orElse("");
        System.out.println(s);


        List<User> userList = Arrays.asList(
                new User(1, "拉拉"),
                new User(2, "two"),
                new User(3, "第三人")
        );
        int totalAge = userList.stream().mapToInt(User::getAge).sum();
        String names = userList.stream().map(User::getName).reduce((a, b) -> a + "," + b).orElse("");
        BigDecimal totalMoney = userList.stream().map(User::getMoney).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        System.out.println(names + ";" + totalAge + ";" + totalMoney);
    }
}
