package com.x.java.tree;

/**
 * create by 许庆之 on 2020/3/16.
 * 内部内实现二叉搜索树
 */
public class BSTree<T extends Comparable<T>> {

    public class Node<E extends Comparable<T>>{
        T data;
        Node<E> left;
        Node<E> right;

        public Node(T data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    private Node<T> root;
    private int size;

    public BSTree(){
        this.root = null;
        this.size = 0;
    }
    public BSTree(T data){
        this.root = new Node<>(data);
        this.size = 1;
    }

    public void insert(T data){
        if(root == null){
            root = new Node<>(data);
        }else{
            root = insert(root,data);
        }
    }

    private Node<T> insert(Node<T> node,T data){
        if(node == null){
            this.size++;
            return new Node<>(data);
        }else{
            if(data.compareTo(node.data)==0) {
                System.out.println("二叉搜索树不允许插入重复元素!!!");
            }else if(data.compareTo(node.data)<0){
                node.left = insert(node.left,data);
            }else{
                node.right = insert(node.right,data);
            }
        }
        return node;
    }

    public void inOrder(){
        if(root == null){
            return;
        }else{
            inOrder(root);
        }
    }
    private void inOrder(Node<T> node){
        if(node == null){
            return;
        }else{
            inOrder(node.left);
            System.out.print(node.data+" ");
            inOrder(node.right);
        }
    }

    public boolean contains(T data){
        if(root == null){
            return false;
        }else {
            return contains(root, data);
        }
    }

    private boolean contains(Node<T> node,T data){
        if(node == null){
            return false;
        }else{
            if(data.compareTo(node.data) == 0){
                return true;
            }else if(data.compareTo(node.data)<0){
                return contains(node.left,data);
            }else{
                return contains(node.right,data);
            }
        }
    }

    public T min(){
        if(root == null){
            return null;
        }else{
            return min(root).data;
        }
    }
    private Node<T> min(Node<T> node){
        if(node.left == null){
            return node;
        }else{
            return min(node.left);
        }
    }

    public T max(){
        if(root == null){
            return null;
        }else{
            return max(root).data;
        }
    }
    private Node<T> max(Node<T> node){
        if(node.right == null){
            return node;
        }else{
            return max(node.right);
        }
    }

    public T removeMin(){
        if(root == null){
            return null;
        }else{
            root = removeMin(root);
            return min();
        }
    }
    private Node<T> removeMin(Node<T> node){
        if(node.left == null){
            Node<T> tempNode = node.right;
            node.right = null;
            size--;
            return tempNode;
        }else{
            node.left = removeMin(node.left);       //关键递归
            return node;
        }
    }

    public T removeMax(){
        if(root == null){
            return null;
        }else{
            root = removeMax(root);
            return max();
        }
    }
    private Node<T> removeMax(Node<T> node){
        if(node.right == null){
            Node<T> tempNode = node.left;
            node.left = null;
            size--;
            return tempNode;
        }else{
            node.right = removeMax(node.right);
            return node;
        }
    }

    public void removeData(T data){
        root = removeData(root,data);
    }

    private Node<T> removeData(Node<T> node,T data){
        if(node == null){
            return null;
        }
        if(data.compareTo(node.data)<0){
            node.left = removeData(node.left,data);
            return node;
        }else if(data.compareTo(node.data)>0){
            node.right = removeData(node.right,data);
            return node;
        }else{
            if(node.left == null){
                Node<T> tempNode = node.right;
                node.right = null;
                size--;
                return tempNode;
            }
            if(node.right == null){
                Node<T> tempNode = node.left;
                node.left = null;
                size--;
                return tempNode;
            }
            //Hibbard Deletion
            Node<T> successorNode = min(node.right);
            successorNode.left = node.left;
            successorNode.right = removeMin(node.right);
            node.left = null;
            node.right = null;
            return successorNode;
        }
    }


    public static void main(String[] args) {
        int[] arr = {5,1,7,4,3,9,10,0};
        BSTree<Integer> bst = new BSTree<>(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            bst.insert(arr[i]);
        }
        System.out.println("中序遍历为: ");
        bst.inOrder();
        System.out.println("\n是否有3    "+bst.contains(3));
        System.out.print("最小元素为：");
        System.out.println(bst.min());
        System.out.print("最大元素为：");
        System.out.println(bst.max());
        System.out.print("删去最小元素：");
        System.out.println(bst.removeMin());
        System.out.print("删去最大元素: ");
        System.out.println(bst.removeMax());
        System.out.println("删去指定元素3: ");
        bst.removeData(3);
        bst.inOrder();
    }

}
