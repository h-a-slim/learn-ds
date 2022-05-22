package com.learn.ds.tree;

import com.learn.ds.tree.Treap.TreapNode;
import com.learn.ds.tree.Treap.TreapNodePair;

public class TreapImplicit {

    public TreapNode insert(TreapNode root, int key, int index) {
        final TreapNode newNode = new TreapNode(key);
        if (root == null) return updateSize(newNode);
        final TreapNodePair splitted = split(root, index);
        return merge(merge(splitted.getFirst(), newNode), splitted.getSecond());
    }

    public TreapNode deleteNode(TreapNode root, int key) {
        return root;
    }

    public void inorder(TreapNode root) {
        if (root == null) return;

        inorder(root.getLeft());
        System.out.println(root);
        inorder(root.getRight());
    }

    public void preorder(TreapNode root) {
        if (root == null) return;

        System.out.println(root);
        preorder(root.getLeft());
        preorder(root.getRight());
    }

    public TreapNodePair split(TreapNode root, int index) {
        return split(root, index, 0);
    }

    private TreapNodePair split(TreapNode root, int index, int countBefore) {
        TreapNodePair splitted = new TreapNodePair(null, null);
        if (root == null) return splitted;
        int currentIndex = countBefore + sizeof(root.getLeft());
        if (index <= currentIndex) {
            TreapNodePair splittedRight = split(root.getRight(), index, currentIndex + 1);
            root.setRight(splittedRight.getFirst());
            splitted = new TreapNodePair(root, splittedRight.getSecond());
        } else {
            TreapNodePair splittedLeft = split(root.getLeft(), index, countBefore);
            root.setLeft(splittedLeft.getSecond());
            splitted = new TreapNodePair(splittedLeft.getFirst(), root);
        }
        updateSize(root);
        return splitted;
    }

    public TreapNode merge(TreapNode first, TreapNode second) {
        if (first == null || second == null) return first == null ?
                updateSize(second) :
                updateSize(first);

        TreapNode merged = null;
        if (first.getPriority() > second.getPriority()) {
            first.setRight(merge(first.getRight(), second));
            merged = first;
        } else {
            second.setLeft(merge(first, second.getLeft()));
            merged = second;
        }
        return updateSize(merged);
    }

    private TreapNode updateSize(TreapNode root) {
        if (root != null) root.setSize(1 + sizeof(root.getLeft()) + sizeof(root.getRight()));
        return root;
    }

    private int sizeof(TreapNode root) {
        return root == null ? 0 : root.getSize();
    }

}
