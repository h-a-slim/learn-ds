package com.learn.ds.tree.treap;

import com.learn.ds.util.Pair;

public class TreapImplicit {

    public TreapNode insert(TreapNode root, int key, int index) {
        final TreapNode newNode = new TreapNode(key);
        if (root == null) return updateSize(newNode);
        final Pair<TreapNode> splitted = split(root, index);
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


    public Pair<TreapNode> split(TreapNode root, int index) {
        Pair<TreapNode> splitted = new Pair<>(null, null);
        if (root == null) return splitted;
        if (sizeof(root.getLeft()) + 1 <= index) {
            Pair<TreapNode> splittedRight = split(root.getRight(), index - sizeof(root.getLeft()) - 1);
            root.setRight(splittedRight.getFirst());
            splitted = new Pair<>(root, splittedRight.getSecond());
        } else {
            Pair<TreapNode> splittedLeft = split(root.getLeft(), index);
            root.setLeft(splittedLeft.getSecond());
            splitted = new Pair<>(splittedLeft.getFirst(), root);
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