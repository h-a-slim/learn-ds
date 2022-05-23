package com.learn.ds.tree.treap;

public interface TreapImplicitOperations {
    void insert(int key, int index);
    TreapNode search(int key);
    void deleteNode(int key);
    void inorder();
    void preorder();
    TreapImplicit split(int key);
    void merge(TreapImplicit mergeWith);
}
