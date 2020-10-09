package com.algorithm.code;

/**
 * @Author: 许庆之 on 2020/9/27.
 * 打印从 1 到 n 的最大的n位数，即当n为2输出从1到99
 */
public class printNumbers {

    char[] nums;
    char[] loop = {'0','1','2','3','4','5','6','7','8','9'};
    int n;
    StringBuilder res;

    public String printNumbers1(int n) {
        this.n=n;
        res = new StringBuilder();
        nums = new char[n];
        dfs1(0);
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }

    private void dfs1(int x){
        if(x==n){
            res.append(String.valueOf(nums)+',');
            return;
        }
        for (char c : loop){
            nums[x] = c;
            dfs1(x+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new printNumbers().printNumbers1(2));
        System.out.println(new printNumbers().printNumbers2(2));
    }


    /* 去除数字前的0，去除第一个数字0*/
    int nine = 0, count = 0, start;
    public String printNumbers2(int n){
        this.n = n;
        res = new StringBuilder();
        nums = new char[n];
        start = n - 1;
        dfs2(0);
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
    private void dfs2(int x) {
        if(x == n) {
            String s = String.valueOf(nums).substring(start);
            if(!s.equals("0")) {res.append(s + ",");}
            if(n - start == nine) {start--;}
            return;
        }
        for(char i : loop) {
            if(i == '9') { nine++;}
            nums[x] = i;
            dfs2(x + 1);
        }
        nine--;
    }
}
