package com.learn.ds.tree.treap;

public interface TreapOperations {
    void insert(int key);
    TreapNode search(int key);
    void deleteNode(int key);
    void inorder();
    void preorder();
    Treap split(int key);
    void merge(Treap mergeWith);
}
