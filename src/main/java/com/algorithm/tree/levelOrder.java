package com.algorithm.tree;

import java.util.*;

/**
 * @Author: 许庆之 on 2020/10/9.
 * 32.从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 */
public class levelOrder {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * BFS 层序遍历  用队列Queue实现
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> out = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            out.add(node.val);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        // 将 list转为 int[]
        return out.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 更快
     */
    public int[] levelOrder1(TreeNode root) {
        int index = 0;
        if (root == null) {
            return new int[0];
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (index != list.size()) {
            TreeNode t = list.get(index);
            if (t.left != null) {
                list.add(t.left);
            }
            if (t.right != null) {
                list.add(t.right);
            }
            index++;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i).val;
        }
        return arr;
    }


    /** 试图使用递归 */
    //    public int[] levelOrder1(TreeNode root) {
    //        if(root==null) return new int[0];
    //        List<TreeNode> tmp = new ArrayList<>();
    //        tmp.add(root);
    //        List<Integer> out = new ArrayList<>();
    //        cur(out,tmp);
    //        return out.stream().mapToInt(Integer::intValue).toArray();
    //    }
    //    private void cur(List<Integer> out,List<TreeNode> tmp){
    //        if(tmp==null){
    //            return;
    //        }
    //        List temp = new ArrayList<TreeNode>();
    //        for(TreeNode node:tmp){
    //            out.add(node.val);
    //            if (node.left!=null) temp.add(node.left);
    //            if (node.right!=null) temp.add(node.right);
    //        }
    //        cur(out,temp);
    //    }

    /**
     * 将结果按 层级返回 每一层一个int[]，并且要一行从左到右，一行从右到左（之字形）
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> nodelist = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                nodelist.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            /* 此处如果直接使用 res.add(nodelist)错误，这样传的是nodelist的地址
            *  当nodelist改变时res中的内容也会改变
            *  所以此处必须new一个ArrayList实现深拷贝  */
            res.add(new ArrayList<>(nodelist));
            nodelist.clear();
        }
        /*  加一个reverse反转满足题目之字形顺序打印*/
        for(int i=1;i<res.size();i+=2){
            Collections.reverse(res.get(i));
        }
        return res;
    }
}