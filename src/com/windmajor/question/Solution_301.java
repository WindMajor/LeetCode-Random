package com.windmajor.question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution_301 {

    /* 350. 两个数组的交集 II */
    public int[] intersect(int[] nums1, int[] nums2) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int value : nums1) {
            list.add(value);
        }

        int[] result = new int[Math.max(nums1.length, nums2.length)];
        int k = 0;

        for (int value : nums2) {
            int index = list.indexOf(value);
            if (index >= 0) {
                result[k] = list.remove(index);
                k += 1;
            }
        }

        return Arrays.copyOf(result, k);
    }

    /* 387. 字符串中的第一个唯一字符 */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();

        for (Character c : chars) {
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
}
