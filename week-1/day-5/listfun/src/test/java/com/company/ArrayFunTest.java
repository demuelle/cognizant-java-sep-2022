package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayFunTest {
    ArrayFun testObj;
    List<List<Integer>> test1;
    List<List<Integer>> test2;
    List<List<Integer>> test3;

    @Before
    public void setUp() throws Exception {
        testObj = new ArrayFun();

        test1 = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        //        list1.add(1);
        //        list1.add(2);
        //        list1.add(3);
        test1.add(list1);

        test2 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list2.add(6);
        list2.add(-4);
        list2.add(2);
        test2.add(list2);

        test3 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(4);
        list4.add(4);
        List<Integer> list5 = new ArrayList<>();
        list5.add(2);
        list5.add(4);
        list5.add(6);
        test3.add(list3);
        test3.add(list4);
        test3.add(list5);
    }

    @Test
    public void shouldReturnAverageOfEachArray() {
        List<Integer> averages1 = new ArrayList<>();
        averages1.add(2);

        List<Integer> averages2 = new ArrayList<>();
        averages2.add(2);
        averages2.add(4);
        averages2.add(4);

        assertEquals(averages1, testObj.returnAverages(test1));
        assertEquals(averages2, testObj.returnAverages(test3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        List<Integer> avg = testObj.returnAverages(test2);
    }
}