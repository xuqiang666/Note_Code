package com.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Create By  xqz on 2020/10/2.
 * 30定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * 俩种解法：1.一个deque加一个辅助deque
 *          2.一个链表，在每个节点中保存当前的最小值
 */
public class MinStack {

    /** 链表实现，在节点中保存当前链表最小值 */
//    public static class ListNode{
//        int val;
//        int min;
//        ListNode next;
//        ListNode(int x,int min,ListNode node) {
//            this.val=x;
//            this.min=min;
//            this.next=node;
//        }
//    }
//    ListNode head;
//    public MinStack() {
//    }
//
//    public void push(int x) {
//        if(head==null){
//            head=new ListNode(x,x,null);
//        }else{
//            // 这一步是精髓啊
//            head=new ListNode(x,Math.min(x,head.val),head);
//        }
//    }
//
//    public void pop() {
//        head = head.next;
//    }
//
//    public int top() {
//        return head.val;
//    }
//
//    public int min() {
//        return head.min;
//    }

    /** 一个deque来存数据，一个deque来存最小值
     * deque既能当栈（push，pop），也能当队列（offer，poll）
     * */
    Deque<Integer> deque;
    Deque<Integer> help;
    public MinStack(){
        deque = new ArrayDeque<>();
        help = new ArrayDeque<>();
    }
    public void push(int x){
        deque.push(x);
        if(help.isEmpty() || help.peek()> x){
            help.push(x);
        }
    }
    public void pop(){
        int e = deque.pop();
        if(!help.isEmpty() && e==help.peek()){
            help.pop();
        }
    }
    public int top(){
        return deque.peek();
    }
    public int min(){
        return help.peek();
    }


}
