package com.company;

import java.util.ArrayList;
import java.util.List;

public class ArrayFun {
    public List<Integer> returnAverages(List<List<Integer>> arr) {
        List<Integer> averages = new ArrayList<>();

        for (List<Integer> subArr : arr) {
            int sum = 0;
            for (int val : subArr) {
                if (val < 0) {
                    throw new IllegalArgumentException();
                }
                sum += val;
            }
            averages.add(sum / subArr.size());
        }

        return averages;
    }
}