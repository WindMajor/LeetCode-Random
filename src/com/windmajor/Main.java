package com.windmajor;

import com.windmajor.question.Solution_001;
import com.windmajor.question.Solution_701;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        int[] result = new Solution_001().twoSum2(new int[]{2, 7, 11, 15}, 9);
//        System.out.println("result[0] = " + result[0]);
//        System.out.println("result[1] = " + result[1]);

//        int result = new Solution_701().numJewelsInStones("z", "ZZ");
//        System.out.println(result);
//
//        int i = new Solution_001().lengthOfLongestSubstring2("dvdf");
//        System.out.println(i);

        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(1);
        list.add(6);
        list.add(4);
        list.add(9);
        list.add(3);
        list.add(6);
        list.add(10);
        list.add(2);
        list.add(8);
        list.add(6);
        list.add(7);

        System.out.println("list = " + list);

        quickSort(list, 0, list.size() - 1);

        System.out.println("list = " + list);

    }

    /* 快速排序代码 */
    static void quickSort(List<Integer> list, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(list, start, end);

            quickSort(list, start, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, end);
        }
    }

    static int partition(List<Integer> list, int start, int end) {
        int pivot = list.get(start);
        int index = start;

        for (int i = start + 1; i <= end; i++) {
            if (list.get(i) <= pivot) {
                index += 1;
                Collections.swap(list, index, i);
            }
        }
        Collections.swap(list, index, start);
        return index;
    }
}
