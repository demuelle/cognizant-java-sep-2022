package edu.smu;

public class AnotherCalculator {
    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a/b;
    }

    public int johnsDivide (int a, int b) {
        int returnVal = 0;
        try {
            returnVal = a/b;
        } catch (ArithmeticException ae) {
            System.out.println("OH NO!!!!");
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return returnVal;
    }
}
