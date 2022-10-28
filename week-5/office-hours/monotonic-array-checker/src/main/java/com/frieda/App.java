package com.frieda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        int i = 1;
        System.out.println("i is " + i++ + ". I was incremented after this line ran.");

        System.out.println("i is " + i + ". I was incremented after this line ran.");
        i = i+1;



        System.out.println("i is " + i + ". No incrementing done.");

        System.out.println("i is " + ++i + ". I was incremented before this line ran.");


        i = i + 1;
        System.out.println("i is " + i + ". I was incremented before this line ran.");


        System.out.println("i is " + i + ". No incrementing done.");

        int[] arrA = {1, 1, 2, 3, 3};  //monotonic
        int[] arrB = {7, 6, 5, 4, 4, 3, 2};  //monotonic
        int[] arrc = {};  //monotonic
        int[] arrd = null;  //monotonic
        int[] arre = {1, 2, 3, 2, 5};  //NOT monotonic
        int[] arrf = {2, 1, 2};  //NOT monotonic
        int[] arrg = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  //monotonic
        int[] arrh = {42}; // monotonic

        System.out.println(printArr(arrA) + " is " + (isMonotonic(arrA) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrB) + " is " + (isMonotonic(arrB) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrc) + " is " + (isMonotonic(arrc) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrd) + " is " + (isMonotonic(arrd) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arre) + " is " + (isMonotonic(arre) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrf) + " is " + (isMonotonic(arrf) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrg) + " is " + (isMonotonic(arrg) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrh) + " is " + (isMonotonic(arrh) ? "monotonic" : "not monotonic"));

        System.out.println("\n\nChecking with reduce\n");

        System.out.println(printArr(arrA) + " is " + (isMonotonicWithReduce(arrA) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrB) + " is " + (isMonotonicWithReduce(arrB) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrc) + " is " + (isMonotonicWithReduce(arrc) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrd) + " is " + (isMonotonicWithReduce(arrd) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arre) + " is " + (isMonotonicWithReduce(arre) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrf) + " is " + (isMonotonicWithReduce(arrf) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrg) + " is " + (isMonotonicWithReduce(arrg) ? "monotonic" : "not monotonic"));
        System.out.println(printArr(arrh) + " is " + (isMonotonicWithReduce(arrh) ? "monotonic" : "not monotonic"));

    }

    public static String printArr(int[] x) {
        if (x == null) return "NULL";
        String returnVal = "{";
        for (int a : x) {
            returnVal = returnVal + " " + a;
        }
        returnVal+="}";
        return returnVal;
    }

    public static boolean isMonotonicWithReduce(int[] nums) {
        // 1 2 3 4 5 6 7
        if (nums == null || nums.length < 2) return true;

        int increasing = Arrays.stream(nums).reduce(nums[0], (a, b) -> (a != nums[0] - 1 && b >= a) ? b : nums[0] - 1);
        int decreasing = Arrays.stream(nums).reduce(nums[0], (a, b) -> (a != nums[0] + 1 && b <= a) ? b : nums[0] + 1);
//        int increasing = numList.stream().reduce(0, (a, b) -> a + b);
//        int decreasing = numList.stream().reduce(0, (a, b) -> a + b);
//        System.out.println("" + increasing + "... " + decreasing);
        return increasing >= nums[0] || decreasing <= nums[0];
        // function with 2 arguments
        //   applies the function to the next value, and the most recent value
        //   (requires some base condition)
        // reduce((a, b) => a + b, 0)
        //    so far, the result is 1, and I still have to process 2 3 4 5 6 7
        //    after round 2, result is 3 (1 + 2), and I still have to process 3 4 5 6 7
        //    after round 3, result is 6 (3 + 3), and I still have to process 4 5 6 7
        //    after round 4, result is 10 (6 + 4), and I still have to process 5 6 7
        //    after round 5, result is 15 (10 + 5), and I still have to process 6 7

    }

        public static boolean isMonotonic(int[] nums) {
        // a null array should return true, as should an array of length 0 or 1
        if (nums == null || nums.length < 2) return true;
        // the thing to return. Assume true because our loop will automatically return
        // as soon as we see that it should be false
        boolean returnVal = true;
        // once the array starts moving in one direction, we need to keep track of that
        // if it starts to move in the opposite direction, then return false
        int direction = 0; //will change to increasing or decreasing once we know

        // go through the array. We will check the current and next element every time
        // and be sure that the direction (increasing or decreasing never changes)
        // the bounds of loop therefore go from 0 to before length-1 so that when
        // we get to the end we don't fall over (index out of bounds)
        for (int i = 0; i < nums.length - 1; i++) {
            if (direction == 0) {
                // if the direction is none...
                //   if current < next ==> change direction to increasing
                if (nums[i] < nums[i+1]) {
                    direction = 1;
                    //   else if current > next ==> change direction to decreasing
                } else if (nums[i] > nums[i+1]) {
                    direction = -1;
                }   // else
                //   else don't change direction (still monotonic so far)
            } else if (direction == -1) {
                //    check that current <= next
                //       if it's not: return false
                if (nums[i] < nums[i+1]) {
                    return false;
                }
            } else { // increasing
                //    check that current >= next
                //       if it's not: return false
                if (nums[i] > nums[i+1]) {
                    return false;
                }
            }
        }
        return returnVal;
    }
}

