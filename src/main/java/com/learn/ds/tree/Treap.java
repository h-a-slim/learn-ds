package com.learn.ds.tree;

import lombok.*;

import java.util.Random;

public class Treap {

    public TreapNode insert(TreapNode root, int key) {
        if (root == null) {
            return new TreapNode(key);
        }

        if (key <= root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority) {
                root = rightRotate(root);
            }
        } else {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority) {
                root = leftRotate(root);
            }
        }
        return root;
    }

    public TreapNode search(TreapNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        }

        if (root.key < key) return search(root.right, key);

        return search(root.left, key);
    }

    public TreapNode deleteNode(TreapNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else if (root.left.priority < root.right.priority) {
            root = leftRotate(root);
            root.left = deleteNode(root.left, key);
        } else {
            root = rightRotate(root);
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public void inorder(TreapNode root) {
        if (root == null) return;

        inorder(root.left);
        System.out.println(root);
        inorder(root.right);
    }

    public void preorder(TreapNode root) {
        if (root == null) return;

        System.out.println(root);
        preorder(root.left);
        preorder(root.right);
    }

    public TreapNodePair split(TreapNode root, int key) {
        if(root == null) return new TreapNodePair(null, null);

        if(root.key <= key) {
            TreapNodePair splittedRight = split(root.right, key);
            root.right = splittedRight.first;
            return new TreapNodePair(root, splittedRight.second);
        } else {
            TreapNodePair splittedLeft = split(root.left, key);
            root.left = splittedLeft.second;
            return new TreapNodePair(splittedLeft.first, root);
        }
    }

    public TreapNode merge(TreapNode first, TreapNode second) {
        if(first == null || second == null) return first == null ? second : first;

        if(first.priority > second.priority) {
            first.right = merge(first.right, second);
            return first;
        } else {
            second.left = merge(first, second.left);
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
        TreapNode x = y.left, T2 = x.right;
        x.right = y;
        y.left = T2;
        return x;
    }

    private TreapNode leftRotate(TreapNode x) {
        TreapNode y = x.right, T2 = y.left;
        y.left = x;
        x.right = T2;
        return y;
    }

    @Getter
    @AllArgsConstructor
    public static class TreapNodePair {
        private TreapNode first;
        private TreapNode second;
    }

    @Data
    @RequiredArgsConstructor
    public static class TreapNode {
        @NonNull
        private Integer key;
        private Integer priority = new Random().nextInt() % 100;
        private TreapNode left = null, right = null;

        public TreapNode(int key, int priority) {
            this(key);
            this.priority = priority;
        }

        @Override
        public String toString() {
            final String leftKey = left == null ? "NULL" : left.key.toString();
            final String rightKey = right == null ? "NULL" : right.key.toString();
            return "TreapNode{" +
                    "key=" + key +
                    ", priority=" + priority +
                    ", left.key=" + leftKey +
                    ", right.key=" + rightKey +
                    '}';
        }

    }
}
