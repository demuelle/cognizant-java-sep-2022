package edu.smu.linked.lists;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        LinkedList linkedList = LinkedList.startWithHead(10);
        linkedList.append(19);
        linkedList.append(2);
        linkedList.append(88);
        linkedList.append(44);
        linkedList.append(105);
        linkedList.print();
    }
}
