package com.algorithm.code;

/**
 * @Author: 许庆之 on 2020/9/28.
 */
public class cuttingRope {

        //数论，贪心--加入尽可能多的3结果最大--最快
        public int cuttingRope(int n) {
            if(n<=3) return n-1;
            int x=0,count=0,res=1;
            int a=n/3;
            int b=n%3;

            if(b==1) return (int)Math.pow(3,a-1)*4;
            if(b==2) return (int)Math.pow(3,a)*2;
            return (int)Math.pow(3,a);
        }

}
