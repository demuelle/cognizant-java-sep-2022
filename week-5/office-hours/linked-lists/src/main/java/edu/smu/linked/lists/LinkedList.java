package edu.smu.linked.lists;

public class LinkedList {
    private Node head;
    private Node currentNode;

    public static LinkedList startWithHead(int x) {
        LinkedList returnVal = new LinkedList();
        Node firstNode = new Node(x);
        returnVal.head = firstNode;
        return returnVal;
    }

    public Node append(int x) {
        Node currentNode = this.head;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        Node newNode = new Node(x);
        currentNode.setNext(newNode);
        return newNode;
    }

    // print list
    public void print() {
        Node currentNode = this.head;
        System.out.print(currentNode.getValue() + "...");
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            System.out.print(currentNode.getValue() + "...");
        }
        System.out.println("");
    }
    // return size
    // find in list
    // get at index
    // sort
    // delete
    // insert at index
    // get current node
    // advance current node
    //

}


