package com.learn.ds.tree;

import com.learn.ds.tree.treap.Treap;
import com.learn.ds.tree.treap.TreapNode;
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
        treap.insert(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);
        treap.inorder();
    }

    @Test
    public void shouldPrintPreorder() {
        final Treap treap = new Treap();
        TreapNode root = new TreapNode(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.preorder();
    }

    @Test
    public void shouldSplitSimpleTree() {
        final Treap treap = new Treap(new TreapNode(50));
        treap.insert(20);
        treap.insert(30);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        treap.inorder();
        System.out.println("====splitted====");
        Treap secondTreap = treap.split(50);
        treap.inorder();
        System.out.println("================");
        secondTreap.inorder();
    }

    @Test
    public void shouldMergeSimpleTree() {
        final Treap treap = new Treap(new TreapNode(50));
        treap.insert(20);
        treap.insert(30);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        treap.inorder();
        System.out.println("====splitted====");
        Treap secondTreap = treap.split(50);
        treap.inorder();
        System.out.println("================");
        secondTreap.inorder();
        System.out.println("===merged===");
        treap.merge(secondTreap);
        treap.inorder();
    }
}