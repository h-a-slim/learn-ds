package com.learn.ds.tree.treap;

import com.learn.ds.util.Pair;

public class Treap implements TreapOperations {
    private TreapNode root;

    public Treap() {
        root = null;
    }

    public Treap(TreapNode root) {
        this.root = root;
    }

    @Override
    public void insert(int key) {
        root = insertHelper(root, key);
    }

    @Override
    public TreapNode search(int key) {
        return searchHelper(root, key);
    }

    @Override
    public void deleteNode(int key) {
        root = deleteNodeHelper(root, key);
    }

    @Override
    public void inorder() {
        inorderHelper(root);
    }

    @Override
    public void preorder() {
        preorderHelper(root);
    }

    @Override
    public Treap split(int key) {
        final Pair<TreapNode> splittedTreap = splitHelper(root, key);
        root = splittedTreap.getFirst();
        return new Treap(splittedTreap.getSecond());
    }

    @Override
    public void merge(Treap mergeWith) {
        root = mergeHelper(this.root, mergeWith.root);
    }

    private TreapNode insertHelper(TreapNode root, int key) {
        if (root == null) {
            return new TreapNode(key);
        }

        if (key <= root.getKey()) {
            root.setLeft(insertHelper(root.getLeft(), key));
            if (root.getLeft().getPriority() > root.getPriority()) {
                root = rightRotate(root);
            }
        } else {
            root.setRight(insertHelper(root.getRight(), key));
            if (root.getRight().getPriority() > root.getPriority()) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    private TreapNode searchHelper(TreapNode root, int key) {
        if (root == null || root.getKey() == key) {
            return root;
        }

        if (root.getKey() < key) return searchHelper(root.getRight(), key);

        return searchHelper(root.getLeft(), key);
    }

    private TreapNode deleteNodeHelper(TreapNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.getKey()) {
            root.setLeft(deleteNodeHelper(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(deleteNodeHelper(root.getRight(), key));
        } else if (root.getLeft() == null) {
            return root.getRight();
        } else if (root.getRight() == null) {
            return root.getLeft();
        } else if (root.getLeft().getPriority() < root.getRight().getPriority()) {
            root = leftRotate(root);
            root.setLeft(deleteNodeHelper(root.getLeft(), key));
        } else {
            root = rightRotate(root);
            root.setRight(deleteNodeHelper(root.getRight(), key));
        }
        return root;
    }

    private void inorderHelper(TreapNode root) {
        if (root == null) return;

        inorderHelper(root.getLeft());
        System.out.println(root);
        inorderHelper(root.getRight());
    }

    private void preorderHelper(TreapNode root) {
        if (root == null) return;

        System.out.println(root);
        preorderHelper(root.getLeft());
        preorderHelper(root.getRight());
    }

    private Pair<TreapNode> splitHelper(TreapNode root, int key) {
        if (root == null) return new Pair<>(null, null);

        if (root.getKey() <= key) {
            Pair<TreapNode> splittedRight = splitHelper(root.getRight(), key);
            root.setRight(splittedRight.getFirst());
            return new Pair<>(root, splittedRight.getSecond());
        } else {
            Pair<TreapNode> splittedLeft = splitHelper(root.getLeft(), key);
            root.setLeft(splittedLeft.getSecond());
            return new Pair<>(splittedLeft.getFirst(), root);
        }
    }

    private TreapNode mergeHelper(TreapNode first, TreapNode second) {
        if (first == null || second == null) return first == null ? second : first;

        if (first.getPriority() > second.getPriority()) {
            first.setRight(mergeHelper(first.getRight(), second));
            return first;
        } else {
            second.setLeft(mergeHelper(first, second.getLeft()));
            return second;
        }
    }

    /*
    T1, T2 and T3 are subtrees of the tree rooted with y (on left side) or x (on right side)
                y                               x
               / \     Right Rotation          /  \
              x   T3   – – – – – – – >        T1   y
             / \       < - - - - - - -            / \
            T1  T2     Left Rotation            T2  T3
    */
    private TreapNode rightRotate(TreapNode y) {
        TreapNode x = y.getLeft(), T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        return x;
    }

    private TreapNode leftRotate(TreapNode x) {
        TreapNode y = x.getRight(), T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        return y;
    }
}
