package com.x.java.tree;

/**
 * create by 许庆之 on 2020/3/16.
 */
public class RedBlackTree {

    private final int R = 1;
    private final int B = 0;

    private Node root;
    class Node{
        int color = R;
        int data;
        Node left;
        Node right;
        Node parent;
        public Node(int data){
            this.data = data;
        }
    }


    public void leftRotate(Node node){
        if(node.parent==null){
            //根节点左旋
            Node right = node.right;
            node.right = right.left;
            right.left.parent = node;
            node.parent = right;
            right.left = node;
            right.parent = null;
        }
    }
}
