package com.windmajor.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution_501 {

    /* 599. 两个列表的最小索引总和 */
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int minIndex = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();

        for (int i = 0; i < list2.length; i++) {
            Integer value = map.get(list2[i]);
            if (value != null) {
                if ((value + i) < minIndex) {
                    minIndex = value + i;
                    list.clear();
                    list.add(list2[i]);
                } else if ((value + i) == minIndex) {
                    list.add(list2[i]);
                }
            }
        }

        String[] result = new String[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
