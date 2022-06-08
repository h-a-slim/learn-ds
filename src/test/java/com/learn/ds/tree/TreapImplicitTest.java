package com.learn.ds.tree;

import com.learn.ds.tree.treap.TreapImplicit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreapImplicitTest {
    private final int[] testData = new int[]{50, 30, 20, 40, 70, 60, 80};
    private final TreapImplicit treap = new TreapImplicit();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < testData.length; i++) {
            treap.insert(testData[i], i + 1);
        }
    }

    @Test
    public void shouldInsertNewNode() {
        treap.inorder();
        System.out.println("=======");
        treap.preorder();
    }

    @Test
    public void shouldDeleteNode() {
        treap.inorder();
        treap.deleteNode(5);
        System.out.println("=======");
        treap.inorder();
    }

}