package com.windmajor;

import com.windmajor.question.Solution_001;
import com.windmajor.question.Solution_701;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int ret = new Solution_001().lengthOfLastWord("a ");
        System.out.println(ret);

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
