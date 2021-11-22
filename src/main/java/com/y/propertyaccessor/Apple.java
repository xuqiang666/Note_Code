package com.y.propertyaccessor;

import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class Apple {

    private String color;

    // 复杂类型
    private Size size = new Size(); // 苹果的尺寸。 存在级联
    private String[] arrStr = new String[1];
    private List<String> listStr = new ArrayList<>();
    private Map<Integer, String> map = new HashMap<>();

    // 更为复杂的类型
    private List<List<String>> listList = new ArrayList<>();
    private List<Map<Integer, String>> listMap = new ArrayList<>();

    public Apple() {
        super();
        listList.add(new ArrayList<>());
        listMap.add(new HashMap<>());
    }

}
