package com.github.peshkovm;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
  private final T value;
  private final List<Tree<T>> children;

  public Tree(T value) {
    this.value = value;
    this.children = new ArrayList<>();
  }

  public Tree<T> addChild(Tree<T> child) {
    children.add(child);

    return this;
  }

  public long numberOfLeaves() {
    long numOfLeaves = 1; // must have root

    return numOfLeaves + recursiveNumberOfLeaves(this);
  }

  private long recursiveNumberOfLeaves(Tree<T> root) {
    if (root.children.isEmpty()) return 0;

    return root.children.size()
        + root.children.stream().mapToLong(this::recursiveNumberOfLeaves).sum();
  }
}
