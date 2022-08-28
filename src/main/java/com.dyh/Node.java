package com.dyh;

/**
 * 节点
 *
 * @author pixel-revolve
 * @date 2022/08/24
 */
public class Node<T extends Comparable<T>> {

    /**
     * 数据域
     */
    public T data;

    /**
     * 节点子树的高度
     */
    public int height;

    /**
     * 左孩子
     */
    public Node<T> leftChild;

    /**
     * 右孩子
     */
    public Node<T> rightChild;

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

}