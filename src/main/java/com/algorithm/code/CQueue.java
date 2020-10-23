package com.algorithm.code;

import java.util.LinkedList;

/**
 * @Author: 许庆之 on 2020/10/21.
 *
 * 09.用俩个栈实现队列
 */
class CQueue {

    /** 在java中使用linkedlist替代stack */
    LinkedList<Integer> stack1;
    LinkedList<Integer> stack2;
    public CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.add(value);
    }
    /** stack1做增加操作，stack2做删除操作
     *  stack1只暂存元素的添加顺序，如果要打印整个队列则是将stack1中的元素都pop然后add到stack2中
     */
    public int deleteHead() {
        if(stack2.isEmpty()){
            if(stack1.isEmpty()){
                return -1;
            }

            while(!stack1.isEmpty()) {
                stack2.add(stack1.pop());
            }
            return stack2.pop();
        }else{
            return stack2.pop();
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
