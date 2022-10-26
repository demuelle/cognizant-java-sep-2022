package com.twou.btree;

import java.util.Objects;

public class Node {
    private String value;
    private Node left;
    private Node right;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value) && Objects.equals(left, node.left) && Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    public String shortString() {
        return "Node with value " + this.value;
    }
    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", left=" + left==null?"null":left.shortString() +
                ", right=" + right==null?"null":right.shortString() +
                '}';
    }
}
