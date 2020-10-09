package com.algorithm.String;

import java.util.HashSet;

/**
 * @Author: 许庆之 on 2020/10/9.
 *
 * 38.字符串的全排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 63ms\45mb
 */
public class permutation {
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
        String s = "abc";
        String[] res = new permutation().permutation(s);
        for (String str:res){
            System.out.println(str);
        }
    }
}
