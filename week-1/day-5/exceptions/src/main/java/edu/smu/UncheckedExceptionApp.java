package edu.smu;

import java.util.Scanner;

public class UncheckedExceptionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = "";
        int number = 0;

        do {
            try {
                System.out.println("Please enter a number between 1 and 10.");
                userInput = scanner.nextLine();
                number = Integer.parseInt(userInput);
            } catch (NumberFormatException nfe) {
                System.out.println("You were supposed to enter a number. " + nfe.getMessage());
                System.out.println(userInput + " is of type  " + userInput.getClass());
            }
        } while (! (number >=1 && number <=10));
    }

}
