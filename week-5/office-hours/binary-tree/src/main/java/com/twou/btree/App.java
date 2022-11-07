package com.twou.btree;


import java.util.Scanner;

public class App {
    public static final int ADD_VALUE_OPTION=1;
    public static final int PRINT_OPTION=2;
    public static final int PRINT_FLAT_OPTION=3;
    public static final int EXIT_OPTION=9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Making a tree!");
        System.out.println("Starting value: ");
        String val = scanner.nextLine();

        Tree myTree = Tree.plant(val);

        int selection = 0;
        do {
            System.out.println("" + ADD_VALUE_OPTION + ": Add a value");
            System.out.println("" + PRINT_OPTION + ": Print the tree (verbose)");
            System.out.println("" + PRINT_FLAT_OPTION + ": Print the sorted values (flattened tree)");
            System.out.println("" + EXIT_OPTION + ": Quit");
            selection = Integer.parseInt(scanner.nextLine());

            switch (selection) {
                case ADD_VALUE_OPTION:
                    System.out.println("Next value: ");
                    myTree.insertValue(scanner.nextLine());
                    break;
                case PRINT_OPTION:
                    myTree.printTree();
                    break;
                case PRINT_FLAT_OPTION:
                    myTree.printFlatInOrder();
                    break;
                case EXIT_OPTION:
                    System.out.println("QUITTING! HERE'S YOUR TREE");
                    System.out.println("\n\n");
                    myTree.printTree();
                    System.out.println("\n\n");
                    System.out.println("And here's the tree flattened: ");
                    myTree.printFlatInOrder();
            }
        } while (selection != EXIT_OPTION);
        System.out.println("\n\nTHE END");
    }
}
