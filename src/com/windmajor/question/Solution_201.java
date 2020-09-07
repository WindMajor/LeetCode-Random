package com.windmajor.question;

import java.util.HashMap;

public class Solution_201 {

    /* 205.同构字符串 */
    public boolean isIsomorphic(String s, String t) {

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();

        int stub = 1;
        for (int i = 0; i < sArray.length; i++) {

            Integer sValue = sMap.get(sArray[i]);
            Integer tValue = tMap.get(tArray[i]);

            if (sValue == null && tValue == null) {
                sMap.put(sArray[i], stub);
                tMap.put(tArray[i], stub);
                stub += 1;

            } else if (sValue != null && tValue != null) {
                if (!sValue.equals(tValue)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /* 219. 存在重复元素 II */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if (value != null && Math.abs(value - i) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
