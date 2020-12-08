package com.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By  xqz on 2020/10/12.
 */
public class letterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        if(S==null||S.length()==0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        char[] chars = S.toCharArray();
        dfs(chars,0,res);
        return res;
    }
    private void dfs(char[] chars,int j,List<String> res){
        if(j==chars.length){
            res.add(String.valueOf(chars));
            return;
        }
        if(chars[j]>='0' && chars[j]<='9'){
            dfs(chars,j+1,res);
        }else if(chars[j]>='A' && chars[j]<='Z'){
            dfs(chars,j+1,res);
            chars[j]= (char) (chars[j]+32);
            dfs(chars,j+1,res);
        }else if(chars[j]>='a' && chars[j]<='z') {
            dfs(chars, j + 1, res);
            chars[j] = (char) (chars[j] - 32);
            dfs(chars, j + 1, res);
        }
    }

    /** 用回溯来实现  backtrace
     *
     * */
    private void backtrace(char[] chars,int j,List<String> res){
        res.add(String.valueOf(chars));
        for (int i = j; i < chars.length; i++) {
            if(chars[j]>='0' && chars[j]<='9'){
                continue;
            }else if(chars[i]>='A' && chars[i]<='Z'){
                chars[i] = (char) (chars[i] + 32);
                backtrace(chars,i+1,res);
                chars[i] = (char) (chars[i] - 32);
            }else if(chars[i]>='a' && chars[i]<='z') {
                chars[i] = (char) (chars[i] - 32);
                backtrace(chars,i+1,res);
                chars[i] = (char) (chars[i] + 32);
            }
        }
    }

}
