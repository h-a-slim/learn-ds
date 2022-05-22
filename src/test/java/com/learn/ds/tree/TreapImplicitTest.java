package com.learn.ds.tree;

import com.learn.ds.tree.Treap.TreapNode;
import org.junit.jupiter.api.Test;

class TreapImplicitTest {

    @Test
    public void shouldInsertNewNode() {
        final TreapImplicit treap = new TreapImplicit();
//        int[] testData = new int[]{50, 30, 20, 40, 70, 60, 80};
        int[] testData = new int[]{30, 20};
        TreapNode root = null;
        for (int i = 1; i <= testData.length; i++) {
            root = treap.insert(root, testData[i - 1], i);
            treap.preorder(root);
            System.out.println("======");
        }

    }
}