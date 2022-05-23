package com.learn.ds.tree.treap;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Data
@RequiredArgsConstructor
public class TreapNode {
    @NonNull
    private Integer key;
    private Integer priority = new Random().nextInt() % 100;
    private TreapNode left = null, right = null;
    private Integer size = 0;

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
                ", size=" + size +
                ", priority=" + priority +
                ", left.key=" + leftKey +
                ", right.key=" + rightKey +
                '}';
    }

}