package com.x.java.tree;

import java.util.Arrays;

/**
 * Create By  xqz on 2020/9/15.
 * 根据前序遍历和中序遍历得到树
 */
public class RebulidTree {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder.length == 0){
                return null;
            }
            int len = preorder.length;
            int rootval = preorder[0];
            TreeNode root = new TreeNode(rootval);
            for(int i=0;i<len;i++){
                if(inorder[i]==rootval){
                    /*
                     Arrays.copyOfRange(int[],from.to) 包括下标from，不包括下标to
                     此处注意前序数组从1开始到index
                    */
                    root.left=buildTree(Arrays.copyOfRange(preorder,1,i+1),Arrays.copyOfRange(inorder,0,i));
                    root.right=buildTree(Arrays.copyOfRange(preorder,i+1,len),Arrays.copyOfRange(inorder,i+1,len));
                    break;
                }
            }
            return root;
        }
    }

    /**
     * java最优解形式，不使用Arrays.copyOfRange，更快
     * */
    public TreeNode findBinaryTree(int[] pre, int preStart, int preEnd, int[] in ,int inStart, int inEnd){
        // 没有符合前序和中序的二叉树或已经到达叶子节点
        if(preStart>preEnd || inStart>inEnd) {
            return null;
        }

        // 根节点
        TreeNode root = new TreeNode(pre[preStart]);

        for(int i=inStart;i<=inEnd;i++){
            if(in[i]==pre[preStart]){
                // i-inStart 为左子树的节点个数，即中序数组匹配到的根节点i 左边的节点数
                root.left = findBinaryTree(pre, preStart+1, i-inStart+preStart, in, inStart, i-1);
                // (i-inStart+preStart)+1：左子树的下一个开始为右子树
                root.right = findBinaryTree(pre, i-inStart+preStart+1, preEnd, in, i+1, inEnd);
            }
        }
        return root;
    }
    // 重建主函数
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root = findBinaryTree(pre, 0, pre.length-1, in, 0, in.length-1);
        return root;
    }


    /**
     * LeetCode最优解
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution2
    {
        int in = 0;
        int pre = 0;

        public TreeNode buildTree(int[] preorder, int[] inorder)
        {
            if(preorder.length == 0 || inorder.length == 0) {
                return null;
            }
            return dfs(preorder, inorder, Integer.MAX_VALUE);
        }

        private TreeNode dfs(int[] preorder, int[] inorder, int end)
        {
            if(pre > preorder.length - 1) {
                return null;
            }

            if(inorder[in] == end)
            {
                in++;
                return null;
            }

            int cur = preorder[pre];
            pre++;

            TreeNode root = new TreeNode(cur);

            root.left = dfs(preorder, inorder, cur);
            root.right = dfs(preorder, inorder, end);

            return root;
        }
    }


}
