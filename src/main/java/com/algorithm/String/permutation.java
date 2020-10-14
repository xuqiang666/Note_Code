package com.algorithm.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: 许庆之 on 2020/10/9.
 *
 * 38.字符串的全排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 */
public class permutation {

    /** 63ms\45mb*/
    public String[] permutation(String s) {
        if(s==null||s.length()==0) return new String[0];
        boolean[] visited = new boolean[s.length()];
        HashSet<String> set = new HashSet<>();
        String letter="";
        dfs(s,letter,visited,set);
        return set.toArray(new String[set.size()]);
    }
    private void dfs(String s,String letter,boolean[] visited,HashSet<String> res){
        if(s.length()==letter.length()){
            res.add(letter);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(visited[i]) continue;
            visited[i]=true;
            dfs(s,letter+c,visited,res);
            visited[i]=false;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String s = "abcdddd";
        String[] res = new permutation().permutation2(s);
//        for (String str:res){
//            System.out.println(str);
//        }
        System.out.println(System.currentTimeMillis()-start+" ms");
    }


    /**  9 ms	42.8 MB   一位一位固定*/
    public String[] permutation2(String s) {
        if(s==null || s.length()==0) return new String[0];
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        dfs2(0,res,chars);
        return res.toArray(new String[res.size()]);
    }
    private void dfs2(int x,List<String> res,char[] chars){
        if(x==chars.length-1){
            res.add(String.valueOf(chars));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < chars.length; i++) {
            if (set.contains(chars[i])){
                continue;
            }
            set.add(chars[i]);
            swap(x,i,chars);
            dfs2(x+1,res,chars);
            swap(x,i,chars);
        }
    }
    private void swap(int i,int j,char[] chars){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
