package com.dyh;

import org.junit.Test;

public class TestCatalogBTree {

    @Test
    public void testCreateTree(){
        /**
         * 添加的顺序不同，最后树的结构也不同
         */
        CatalogBTree<Integer,String> bt = new CatalogBTree<>();
        bt.put(5,"a");
        bt.put(8,"b");
        //     bt.put(9,"c");
        bt.put(10,"d");
        bt.put(15,"e");
        //     bt.put(18,"f");
        bt.put(20,"g");
        bt.put(26,"h");
        //      bt.put(27,"i");

        bt.put(28,"i");
        bt.put(30,"j");
        //      bt.put(33,"k");
        bt.put(35,"l");
        bt.put(38,"m");
        //    bt.put(50,"n");
        bt.put(56,"o");
        bt.put(60,"p");
        //      bt.put(63,"q");

        bt.put(65,"r");
        bt.put(73,"s");
        //       bt.put(79,"t");
        bt.put(80,"u");
        bt.put(85,"v");
        //   bt.put(88,"w");
        bt.put(90,"x");
        bt.put(96,"y");
        bt.put(99,"z");


        /**
         * 插入第三个元素
         */
        bt.put(9,"c");
        bt.put(18,"f");
        bt.put(27,"i");
        bt.put(33,"k");
        bt.put(50,"n");
        bt.put(63,"q");
        bt.put(79,"t");
        bt.put(88,"w");

        System.out.println("高度："+bt.height());
        System.out.println("查询："+bt.get(9));
        System.out.println( bt.toString());
    }

}
