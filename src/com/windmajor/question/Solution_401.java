package com.windmajor.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_401 {

    /* 412. Fizz Buzz */
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {

            boolean divisibleBy3 = (i % 3 == 0);
            boolean divisibleBy5 = (i % 5 == 0);

            if (divisibleBy3 && divisibleBy5) {
                ans.add("FizzBuzz");
            } else if (divisibleBy3) {
                ans.add("Fizz");
            } else if (divisibleBy5) {
                ans.add("Buzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }

    public List<String> fizzBuzz2(int n) {
        List<String> ans = new ArrayList<>();

        for (int i = 1; i <= n; i++) {

            boolean divisibleBy3 = (i % 3 == 0);
            boolean divisibleBy5 = (i % 5 == 0);

            StringBuilder sb = new StringBuilder();

            if (divisibleBy3) {
                sb.append("Fizz");
            }
            if (divisibleBy5) {
                sb.append("Buzz");
            }

            if (!divisibleBy3 && !divisibleBy5) {
                sb.append(i);
            }
            ans.add(sb.toString());
        }
        return ans;
    }

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
