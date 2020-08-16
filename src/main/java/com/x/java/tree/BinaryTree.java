package com.x.java.tree;

/**
 * create by 许庆之 on 2020/3/16.
 * 二叉搜索树的简单实现
 */
public class BinaryTree {

    private int data;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int data){
        this.data = data;
    }

    public void insert(BinaryTree root, int data){
        if(root != null){
            if(root.data<data){
                if(root.right == null){
                    root.right = new BinaryTree(data);
                }else{
                    insert(root.right,data);
                }
            }else{
                if(root.left == null){
                    root.left = new BinaryTree(data);
                }else{
                    insert(root.left,data);
                }
            }
        }
    }

    public void in(BinaryTree root){
        if(root != null){
            in(root.left);
            System.out.print(root.data+" ");
            in(root.right);
        }
    }

    public static void main(String[] args) {
        int[] array = {5,2,6,10,4,7,8,1};
        BinaryTree root = new BinaryTree(array[0]);
        for (int i = 1; i < array.length; i++) {
            root.insert(root,array[i]);
        }
        System.out.println("中序遍历后：");
        root.in(root);
    }
}
