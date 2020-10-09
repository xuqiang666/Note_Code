package com.algorithm.tree;

/**
 * @Author: 许庆之 on 2020/10/9.
 *
 * 33.二叉搜索树的后序遍历序列
 * 输入一个后序遍历的数组，判断它是否合法
 */
public class verifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length<2) return true;
        return cur(postorder,0,postorder.length-1);
    }
    private boolean cur(int[] postorder,int i,int j){
        if(i>=j) return true;
        int p=i;
        /* 根结点必为树后序遍历的最后一位，它的左边都小于它，右边都大于它*/
        while(postorder[p]<postorder[j]){
            p++;
        }
        /* 记录分割左子树和右子树的位置*/
        int m=p;
        while(postorder[p]>postorder[j]){
            p++;
        }
        /* p==j作为判断条件 */
        return p==j && cur(postorder,i,m-1) && cur(postorder,m,j-1);
    }
}
