package com.learn.ds.tree;

import com.learn.ds.tree.Treap.TreapNode;
import com.learn.ds.tree.Treap.TreapNodePair;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TreapTest {

    @Test
    public void shouldCreateNewNode() {
        final TreapNode node = new TreapNode(1, 2);
        assertThat(node).isNotNull();
        assertThat(node.getKey()).isEqualTo(1);
        assertThat(node.getPriority()).isEqualTo(2);
    }

    @Test
    public void shouldPrintInorder() {
        final Treap treap = new Treap();
        TreapNode root = null;
        root = treap.insert(root, 50);
        root = treap.insert(root, 30);
        root = treap.insert(root, 20);
        root = treap.insert(root, 40);
        root = treap.insert(root, 70);
        root = treap.insert(root, 60);
        root = treap.insert(root, 80);
        treap.inorder(root);
    }

    @Test
    public void shouldPrintPreorder() {
        final Treap treap = new Treap();
        TreapNode root = new TreapNode(50);
        root = treap.insert(root, 30);
        root = treap.insert(root, 20);
        root = treap.insert(root, 40);
        root = treap.insert(root, 70);
        root = treap.insert(root, 60);
        treap.preorder(root);
    }

    @Test
    public void shouldSplitSimpleTree() {
        final Treap treap = new Treap();
        TreapNode root = new TreapNode(50);
        root = treap.insert(root, 20);
        root = treap.insert(root, 30);
        root = treap.insert(root, 40);
        root = treap.insert(root, 70);
        root = treap.insert(root, 60);
        root = treap.insert(root, 80);

        treap.preorder(root);
        System.out.println("====splitted====");
        TreapNodePair splitted = treap.split(root, 50);
        treap.preorder(splitted.getFirst());
        System.out.println("================");
        treap.preorder(splitted.getSecond());
    }

    @Test
    public void shouldMergeSimpleTree() {
        final Treap treap = new Treap();
        TreapNode root = new TreapNode(50);
        root = treap.insert(root, 20);
        root = treap.insert(root, 30);
        root = treap.insert(root, 40);
        root = treap.insert(root, 70);
        root = treap.insert(root, 60);
        root = treap.insert(root, 80);

        treap.preorder(root);
        System.out.println("====splitted====");
        TreapNodePair splitted = treap.split(root, 50);
        treap.preorder(splitted.getFirst());
        System.out.println("================");
        treap.preorder(splitted.getSecond());
        System.out.println("===merged===");
        root = treap.merge(splitted.getFirst(), splitted.getSecond());
        treap.preorder(root);
    }
}