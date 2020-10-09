package com.algorithm.matrix;

/**
 * @Author: 许庆之 on 2020/9/29.
 *  26树的子结构
 * 在二叉树中找子树    递归思想，类似与 MatrixExist
 */
public class isSubStructure {
    private class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B==null||A==null) {
            return false;
        }
        if(A.val==B.val){
            if( checkTree(A.left,B.left) && checkTree(A.right,B.right) ){
                return true;
            }
        }
        return isSubStructure(A.left,B)||isSubStructure(A.right,B);
    }
    private boolean checkTree(TreeNode A,TreeNode B){
        if(B==null) return true;
        if(A==null) return false;
        if(A.val!=B.val) return false;
        return checkTree(A.left,B.left) && checkTree(A.right,B.right);
    }


    /**
     * 27 二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }

    /**
     * 28 检查一个二叉树是不是对称的，即左右互为镜像
     * */
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return false;
        return checkSame(root.left,root.right);
    }
    private boolean checkSame(TreeNode A,TreeNode B){
        if(A==null&&B==null) return true;
        if(A==null||B==null) return false;
        if(A.val!=B.val) return false;
        return checkSame(A.left,B.right) && checkSame(A.right,B.left);
    }
}
