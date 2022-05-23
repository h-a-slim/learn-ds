package com.learn.ds.tree;

import com.learn.ds.tree.treap.TreapNode;
import com.learn.ds.tree.treap.TreapImplicit;
import org.junit.jupiter.api.Test;

class TreapImplicitTest {

    @Test
    public void shouldInsertNewNode() {
        final TreapImplicit treap = new TreapImplicit();
        int[] testData = new int[]{50, 30, 20, 40, 70, 60, 80};
        TreapNode root = null;
        for (int i = 0; i < testData.length; i++) {
            root = treap.insert(root, testData[i], i + 1);
        }
        treap.inorder(root);
    }
}