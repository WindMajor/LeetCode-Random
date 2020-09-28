package com.windmajor.question;

import java.util.HashMap;
import java.util.Map;

public class Solution_401 {

    /* 454. 四数相加 II */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        int ans = 0;
        for (int a : A) {
            for (int b : B) {
                int sumAB = a + b;
                map.put(sumAB, map.getOrDefault(sumAB, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                int sumCD = -1 * (c + d);
                if (map.containsKey(sumCD)) {
                    ans += map.get(sumCD);
                }
            }
        }
        return ans;
    }
}
