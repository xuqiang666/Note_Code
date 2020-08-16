package com.x.java.code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpTest {

    //提前定义Pattern表达式预编译
    //Pattern表达式要定义为static final静态变量，避免执行多次预编译
    private static Pattern p = Pattern.compile(".*?(?=\\()");
    public static void main(String[] args) {
        String str = "北京市(朝阳区)(西城区)(海淀区)";
        //Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group());
        }
    }
}