package com.algorithm;

/**
 * @Author: 许庆之 on 2020/9/27.
 */
public class fastPow {
    /**
     * 快速幂的核心在于将幂指数转成额二进制
     * 6对应的二进制为 110    a^6 = a^4 * a^2 * a^0
     * */

    /*普通版本*/
    public int fastpow(int a,int b){
        int ans = 1;
        while(b>0){
            if(b%2==1){
                ans = ans * a;
            }
            b = b/2;
            a = a*a;
        }
        return ans;
    }

    /*移位版本*/
    public int fastpow2(int a,int b){
        int ans = 1;
        while(b>0){
            if((b&1)==1){
                ans*=a;
            }
            b>>=1;
            a*=a;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new fastPow().fastpow(2,6));
        System.out.println(new fastPow().fastpow2(2,6));
    }
}
