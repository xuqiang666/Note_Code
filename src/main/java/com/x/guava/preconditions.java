package com.x.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: 许庆之 on 2020/12/4.
 */
public class preconditions {

    @Test
    public void test() {
        HashMap<String, String> map = new HashMap<>();
        map.put("123","456");
        test1("null",19, null);
    }

    public static void test1(String name, int age, Map<String,String> extInfo) {
        Preconditions.checkNotNull(name,"name must be given!");
        Preconditions.checkArgument(age > 18,"you age is under 18!");
        Map<String, String> defaultExtMap = Maps.newHashMap();
        defaultExtMap.put("sex","man");
        extInfo = Optional.ofNullable(extInfo).orElse(defaultExtMap);

        extInfo.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }
}
