package com.dyh;

/**
 * @author pixel-revolve
 * @date 2022/9/22
 */
public class CatalogBTree<Key extends Comparable<Key>, Value> {

    //参数M 定义每个节点的链接数
    private static final int M = 4;

    private Node root;

    //树的高度  最底层为0 表示外部节点    根具有最大高度，此高度是根之后的高度
    private int height;

    //键值对总数
    private int n;

    //节点数据结构定义
    private static final class Node{
        //值的数量
        private int m;
        private Entry[] children = new Entry[M];
        private Node(int k){
            m = k;
        }
    }

    //节点内部每个数据项定义
    private static class Entry{
        private Comparable key;
        private final Object val;
        //下一个节点
        private Node next;
        public Entry(Comparable key, Object val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Entry [key=");
            builder.append(key);
            builder.append("]");
            return builder.toString();
        }

    }

    public CatalogBTree(){
        root = new Node(0);
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int height(){
        return height;
    }

    /**
     * 查询接口
     * @param key
     * @return
     */
    public Value get(Key key){
        return search(root, key, height);
    }

    private Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;//首次进入，相当于根节点
        //外部节点  这里使用顺序查找
        //如果M很大  可以改成二分查找
        if(ht == 0){//到了有数据的子节点（也可能是根节点，如果键值对小于M），此次查询数据。
            for(int j=0; j<x.m; j++){
                if(equal(key, x.children[j].key))//遍历查询
                    return (Value)children[j].val;
            }
        }
        //内部节点  寻找下一个节点
        else{
            for(int j=0; j<x.m; j++){
                //最后一个节点  或者 插入值小于某个孩子节点值
                if(j+1==x.m || less(key, x.children[j+1].key))//定位数据在哪个节点下，然后继续递归查询
                    return search(x.children[j].next, key, ht-1);//去下一层继续查询
            }
        }
        return null;
    }

    /**
     * 新增数据接口
     * @param key
     * @param val
     */
    public void put(Key key, Value val){
        //插入后的节点，如果节点分裂，则返回分裂后产生的新节点
        Node u = insert(root, key, val, height);
        n++;//键值对总数加一
        if(u == null) return;//根节点没有分裂  直接返回
        //分裂根节点,已满节点进行分裂，将已满节点后M/2节点生成一个新节点，并将新节点的第一个元素指向父节点，此处是M>>1 = 2（因为M=4）
        Node t = new Node(M>>1);
        //旧根节点第一个孩子和新分裂节点第一个孩子 共同 组成新节点作为根
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;//新的根节点
        height++;//节点高度加1
    }

    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        //新建待插入数据数据项
        Entry t = new Entry(key, val, null);
        if(ht == 0){//高度为零时，直接遍历
            for(j=0; j<h.m; j++){ // 外部节点  找到带插入的节点位置j
                if(less(key, h.children[j].key))
                    break;
            }
        }else{
            //内部节点  找到合适的分支节点
            for(j=0; j<h.m; j++){
                if(j+1==h.m || less(key, h.children[j+1].key)){
                    Node u = insert(h.children[j++].next, key, val, ht-1);
                    if(u == null) return null;
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }
        //j为带插入位置，将顺序数组j位置以后后移一位(从后往前处理) 将t插入
        for(int i=h.m; i>j; i--){
            h.children[i] = h.children[i-1];
        }
        System.out.println(j + t.toString());
        h.children[j] = t;//此处插入成功
        h.m++;//值的数量加一
        if(h.m < M) return null;
            //如果节点已满  则执行分类操作（从根节点开始）
        else return split(h);
    }

    private Node split(Node h) {
        //将已满节点h的后，一般M/2节点后的节点分裂到新节点并返回
        Node t = new Node(M/2);
        h.m = M / 2;
        for(int j=0; j<M/2; j++){
            t.children[j] = h.children[M/2+j];//把h节点中M/2节点后的节点分裂到新节点
            h.children[M/2+j] = null;//把h节点中M/2节点后的节点设置为空，以尽快GC
        }
        return t;//返回新节点
    }

    /**
     * 删除数据
     * @param key
     */
    public void remove(Key key){
        remove(root, key, height);

    }

    private void remove(Node x, Key key, int ht){
        Entry[] children = x.children;//首次进入，相当于根节点
        //外部节点  这里使用顺序查找
        //如果M很大  可以改成二分查找
        if(ht == 0){//到了有数据的子节点（也可能是根节点，如果键值对小于M），此次查询数据。
            for(int j=0; j<x.m; j++){
                if(equal(key, x.children[j].key)){//遍历查询
                    children[j] = null;//删除此节点数据
                    x.m--;//值的数量减一
                }
            }
        }
        //内部节点  寻找下一个节点
        else{
            for(int j=0; j<x.m; j++){
                //最后一个节点  或者 插入值小于某个孩子节点值
                if(j+1==x.m || less(key, x.children[j+1].key))//定位数据在哪个节点下，然后继续递归查询
                    remove(x.children[j].next, key, ht-1);//去下一层继续查询
            }
        }
    }

    private boolean equal(Comparable k1, Comparable k2){
        return k1.compareTo(k2)==0;
    }

    private boolean less(Comparable k1, Comparable k2){
        return k1.compareTo(k2)<0;
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;//此数据相当于根节点

        //外部节点
        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + "(" + children[j].key + ")\n");
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }

}