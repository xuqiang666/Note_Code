package com.algorithm.tree;

/**
 * Create By  xqz on 2020/9/6.
 */
public class reverseNode {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        public int[] reversePrint(ListNode head) {
            int count=0;
            ListNode node = head;
            while(node!=null){
                count++;
                node=node.next;
            }
            int[] out = new int[count];
            node = head;
            for (int i = count-1; i >=0 ; i--) {
                out[i] = node.val;
                node = node.next;
            }
            return out;
        }
    }
}
