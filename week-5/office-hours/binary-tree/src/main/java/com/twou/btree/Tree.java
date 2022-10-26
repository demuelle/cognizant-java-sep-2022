package com.twou.btree;

public class Tree {
    private Node root;

    public static Tree plant(String initialValue) {
        Tree returnVal = new Tree();
        Node initialRoot = new Node(initialValue);
        returnVal.root = initialRoot;
        return returnVal;
    }

    public void insertValue(String newVal) {
        insertValue(newVal, root);
    }

    private void insertValue(String newVal, Node node) {
        // compare newVal to root Val
        // if newVal < root val
        if (node.getValue().compareTo(newVal) > 0) {
            //   if root.left is null
            if (node.getLeft() == null) {
                //      make a new node and put it to the left of the root
                node.setLeft(new Node(newVal));
                //      return
                return;
                //   else
            } else {
                //       call insert from the left node
                insertValue(newVal, node.getLeft());
            }
            // else
        } else {
            //   if root.right is null
            if (node.getRight() == null) {
                //       make a new node and put it to the right of the root
                node.setRight(new Node(newVal));
                return;
            } else {
                insertValue(newVal, node.getRight());
            }
        }
    }

    public void printTree() {
        printTree(root, "");
    }

    public void printTree(Node node, String indent) {
        System.out.println(indent + "This node's value is " + node.getValue());
        indent = indent + "   ";
        System.out.println(indent + "To the left... ");
        if (node.getLeft() == null) {
            System.out.println(indent + "EMPTY!");
        } else {
            printTree(node.getLeft(), indent);
        }
        System.out.println(indent + "To the right... ");
        if (node.getRight() == null) {
            System.out.println(indent + "EMPTY!");
        } else {
            printTree(node.getRight(), indent);
        }
    }

    // print items out in order
    public void printFlatInOrder() {
        System.out.println("Printing out the sorted tree");
    }

    public boolean searchTree() {
        // see if an item is in the tree
        // say how many compares were required to complete this search
        return false;
    }

    public Tree rebalance() {
        // make the tree balanced on all legs
        return null;
    }
}

