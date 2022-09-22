package com.dyh;

import org.junit.Test;

public class TestAvlTree {

    private static final Integer[] arrays = new Integer[]{26, 21, 30, 50, 60, 66, 68, 70};
    private static final Integer[] array2=new Integer[]{4520,330,9882,284,3585,9800,9975,105,null,1543,3663,4550,9816,null,null,null,null,593,1999,3622,3979,null,8559,null,null,511,1374,1877,3171,null,null,3669,4370,6337,8694,null,null,1178,1417,1851,null,2303,3226,null,3941,4162,4446,4750,7496,8593,9236,879,1350,null,null,1633,null,2207,3087,3213,3259,null,null,null,null,null,null,null,5379,6372,7860,8586,8654,9058,9422,622,1131,1255,null,1578,1839,2177,null,2510,3142,null,null,null,3452,5001,5811,null,7435,7858,8146,null,null,null,null,null,null,9295,9555,601,null,1028,null,null,null,null,null,null,null,null,null,2333,3005,null,null,3392,null,null,5245,5697,6074,7337,null,7497,null,7948,8473,null,null,null,null,null,null,null,null,null,2351,2634,3086,3273,null,5222,5246,5481,5756,5947,6299,6600,7353,null,null,null,null,8199,8558,null,null,null,2966,null,null,null,null,5056,null,null,null,null,5582,null,null,null,6030,null,null,6518,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,6430,6533,null,null,null,null};


    @Test
    public void testCreateTree(){
        AvlTree<Integer> avlTree = new AvlTree<>();
        for (Integer data : array2) {
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
