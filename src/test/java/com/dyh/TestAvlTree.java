package com.dyh;

import org.junit.Test;

public class TestAvlTree {

    private static final Integer[] arrays = new Integer[]{26, 21, 30, 50, 60, 66, 68, 70};

    @Test
    public void testCreateTree(){
        AvlTree<Integer> avlTree = new AvlTree<>();
        for (Integer data : arrays) {
            avlTree.createTree(data);
        }
        avlTree.printTree();
    }

    @Test
    public void testDeleteTree(){
        AvlTree<Integer> avlTree = new AvlTree<>();
        for (Integer data : arrays) {
            avlTree.createTree(data);
        }
        avlTree.printTree();

        System.out.println("删除掉26");

        avlTree.remove(26);

        avlTree.printTree();
    }

}
