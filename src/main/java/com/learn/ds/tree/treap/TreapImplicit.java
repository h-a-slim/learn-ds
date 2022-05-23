package com.learn.ds.tree.treap;

import com.learn.ds.util.Pair;

public class TreapImplicit implements TreapImplicitOperations{
    private TreapNode root;
    public TreapImplicit() {
        root = null;
    }

    public TreapImplicit(TreapNode root) {
        this.root = root;
    }

    @Override
    public void insert(int key, int index) {
        root = insertHelper(root, key, index);
    }

    @Override
    public TreapNode search(int key) {
        return null;
    }

    @Override
    public void deleteNode(int key) {
        deleteNodeHelper(root, key);
    }

    @Override
    public void inorder() {
        inorderHelper(root, 0);
    }

    @Override
    public void preorder() {
        preorderHelper(root, 0);
    }

    @Override
    public TreapImplicit split(int key) {
        final Pair<TreapNode> splitted = splitHelper(root, key);
        root = splitted.getFirst();
        return new TreapImplicit(splitted.getSecond());
    }

    @Override
    public void merge(TreapImplicit mergeWith) {
        root = mergeHelper(this.root, mergeWith.root);
    }

    private TreapNode insertHelper(TreapNode root, int key, int index) {
        final TreapNode newNode = new TreapNode(key);
        if (root == null) return updateSize(newNode);
        final Pair<TreapNode> splitted = splitHelper(root, index);
        return mergeHelper(mergeHelper(splitted.getFirst(), newNode), splitted.getSecond());
    }

    private TreapNode deleteNodeHelper(TreapNode root, int key) {
        return root;
    }

    private void inorderHelper(TreapNode root, int position) {
        if (root == null) return;
        int curPosition = position + sizeof(root.getLeft());
        inorderHelper(root.getLeft(), position);
        System.out.println(root + " --> Position: " + curPosition);
        inorderHelper(root.getRight(), curPosition + 1);
    }

    private void preorderHelper(TreapNode root, int position) {
        if (root == null) return;
        int curPosition = position + sizeof(root.getLeft());
        System.out.println(root + " --> Position: " + curPosition);
        preorderHelper(root.getLeft(), position);
        preorderHelper(root.getRight(), curPosition + 1);
    }

    private Pair<TreapNode> splitHelper(TreapNode root, int index) {
        Pair<TreapNode> splitted = new Pair<>(null, null);
        if (root == null) return splitted;
        if (sizeof(root.getLeft()) + 1 <= index) {
            Pair<TreapNode> splittedRight = splitHelper(root.getRight(), index - sizeof(root.getLeft()) - 1);
            root.setRight(splittedRight.getFirst());
            splitted = new Pair<>(root, splittedRight.getSecond());
        } else {
            Pair<TreapNode> splittedLeft = splitHelper(root.getLeft(), index);
            root.setLeft(splittedLeft.getSecond());
            splitted = new Pair<>(splittedLeft.getFirst(), root);
        }
        updateSize(root);
        return splitted;
    }

    private TreapNode mergeHelper(TreapNode first, TreapNode second) {
        if (first == null || second == null) return first == null ?
                updateSize(second) :
                updateSize(first);

        TreapNode merged = null;
        if (first.getPriority() > second.getPriority()) {
            first.setRight(mergeHelper(first.getRight(), second));
            merged = first;
        } else {
            second.setLeft(mergeHelper(first, second.getLeft()));
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
