package com.challenging;

public class ArrayUtils {
    public int sumArrays(int[] arr1, int[] arr2) {
        int sum = 0;
        for(int num: arr1) {
            sum += num;
        }
        for (int num: arr2) {
            sum += num;
        }
        return sum;
    }
/*
Add a failing test for arrayify.
arrayify should take in two integers. It should create an array of the
length of the first integer that contains consecutive integers starting
at the second integer. For example, arrayify(3,5) returns [5,6,7].
 */
    public int[] arrayify (int size, int start) {
        int[] returnVal = new int[size];

        // do something!
        for(int i = 0; i < size; i++) {
            returnVal[i] = start++;
        }

        return returnVal;
    }
}
