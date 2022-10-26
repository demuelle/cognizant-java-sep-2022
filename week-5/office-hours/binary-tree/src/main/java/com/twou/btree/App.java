package com.twou.btree;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Making a tree!");
        System.out.println("Starting value: ");
        String val = scanner.nextLine();

        Tree myTree = Tree.plant(val);

        int selection = 0;
        do {
            System.out.println("1: Add a value");
            System.out.println("2: Print the tree");
            System.out.println("3: Quit");
            selection = Integer.parseInt(scanner.nextLine());

            switch (selection) {
                case 1:
                    System.out.println("Next value: ");
                    myTree.insertValue(scanner.nextLine());
                    break;
                case 2:
                    myTree.printTree();
                    break;
                case 3:
                    System.out.println("QUITTING! HERE'S YOUR TREE");
                    System.out.println("\n\n");
                    myTree.printTree();
            }
        } while (selection != 3);
        System.out.println("\n\nTHE END");
    }
}
