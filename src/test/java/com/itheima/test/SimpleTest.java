package com.itheima.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SimpleTest {

    @Test
    public void computeIfAbsentTest() {
        HashMap map = new HashMap();
        String key = "lalal";
        String v = "vu";

        ((HashSet<String>) map.computeIfAbsent(key, k -> new HashSet<String>())).add(v);

        HashMap<String, ArrayList<String>> map2 = new HashMap<>();
        String key2 = "lalal";
        String v2 = "vu";

        ((List)map2.computeIfAbsent(key2, (k) -> {
            return new ArrayList<>();
        })).add(v);
    }

    @Test
    public void test39943(){
        Object a = BigDecimal.valueOf(Long.parseLong("1"));
        Object b = null;
        // null进行强转不报错
        BigDecimal a1 = (BigDecimal) b;
        if(a1 == null) {
            System.out.println("0");
        }else {
            System.out.println("1");
        }
    }

}
