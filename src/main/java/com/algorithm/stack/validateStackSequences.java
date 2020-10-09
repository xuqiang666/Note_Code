package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: 许庆之 on 2020/10/9.
 *
 *  31 栈的压入、弹出序列
 *  输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 *  假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
 *  序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 *  但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 */
public class validateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> deque = new ArrayDeque<>();
        int j=0;
        // 按顺序将pushed的元素压入，当栈顶等于popped元素时表示这个元素要弹出
        for(int elem:pushed){
            deque.push(elem);
            // 这个地方必须用while，不能用if
            while(!deque.isEmpty() && deque.peek()==popped[j]){
                deque.pop();
                j++;
            }
        }
        // 最后会多执行一次j++所以这里直接用length
        return j==pushed.length;
    }

    /***************************/
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        // 递归
        Deque<Integer> deque = new ArrayDeque<>();
        int len = pushed.length;
        boolean flag = true;
        return cur(pushed,popped,deque,0,0,true);
    }

    private boolean cur(int[] pushed,int[] popped,Deque<Integer> temp,int i,int j,boolean flag){
        if(i>pushed.length-1) {
            flag=false;
        }
        if(j>popped.length-1) {
            return false;
        }
        // push
        if(flag){
            temp.push(pushed[i++]);
        }
        // pop
        if(!flag) {
            /// 为什么在此处pop报错NoSuchElementException
            int tt = temp.pop();
            if(tt==popped[j++]){
                if(j==popped.length-1) return true;
            }else{
                return false;
            }
        }
        return cur(pushed,popped,temp,i,j,true) || cur(pushed,popped,temp,i,j,false);
    }

    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(new com.algorithm.stack.validateStackSequences().validateStackSequences(pushed,popped));
    }
}
