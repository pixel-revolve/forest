package com.dyh;

import org.junit.Test;

public class TestBsTree {

    private static final Integer[] arrays = new Integer[]{50, 66, 60, 26, 21, 30, 70, 68};

    @Test
    public void testCreateBsTree(){
        BsTree<Integer> bsTree = new BsTree<>();
        for (Integer data : arrays) {
            bsTree.createTree(data);
        }
        bsTree.printTree();
    }

    @Test
    public void testDeleteNode() {
        BsTree<Integer> bsTree = new BsTree<>();
        for (Integer data : arrays) {
            bsTree.createTree(data);
        }
        bsTree.deleteNode(50);
        bsTree.printTree();
    }

}
