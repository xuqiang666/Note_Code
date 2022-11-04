package com.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 许庆之 on 2020/11/20.
 * https://juejin.cn/post/6844903927037558792
 */
public class LRUCache<k, v> {

    private int capacity;
    private int count;
    private Map<k, Node<k, v>> nodeMap;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        //初始化头节点和尾节点，利用哨兵模式减少判断头结点和尾节点为空的代码
        Node headNode = new Node<>(null, null);
        Node tailNode = new Node<>(null, null);
        this.head = headNode;
        this.tail = tailNode;
    }

    public void put(k key, v value) {
        Node<k, v> node = nodeMap.get(key);
        if (node == null) {
            if (count >= capacity) {
                removeNode();
            }
            node = new Node<>(key, value);
            addNode(node);
        } else {
            moveNodeToHead(node);
        }
    }

    public Node<k, v> get(k key) {
        Node<k, v> node = nodeMap.get(key);
        if (node != null) {
            moveNodeToHead(node);
        }
        return node;
    }

    private void removeNode() {
        Node node = tail.pre;
        //从尾节点处删除，从map中删除
        removeFromTail(node);
        nodeMap.remove(node.key);
        count--;
    }

    private void removeFromTail(Node<k, v> node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
    }

    private void addNode(Node<k, v> node) {
        //从头节点处插入，往map中插入
        addNodeToHead(node);
        nodeMap.put(node.key, node);
        count++;
    }

    private void addNodeToHead(Node<k, v> node) {
        Node next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    private void moveNodeToHead(Node<k, v> node) {
        removeFromTail(node);
        addNodeToHead(node);
    }


    class Node<k, v> {
        k key;
        v value;
        Node pre;
        Node next;

        private Node(k key, v value) {
            this.key = key;
            this.value = value;
        }
    }

}
