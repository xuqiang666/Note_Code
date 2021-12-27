package com.x.stream;

import com.google.common.collect.Lists;

import java.util.List;

public class MapReduceTEst {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("我阿达发 ", "答复啊", "而非微软给", "而我认为");
        String s = list.stream().map(a -> a + "\n")
                .reduce((a, b) -> a + "" + b)
                .get();
        System.out.println(s);
    }
}
