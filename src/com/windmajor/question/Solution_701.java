package com.windmajor.question;

import java.util.HashMap;
import java.util.HashSet;

public class Solution_701 {

    /* 724. 寻找数组的中心索引 */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        int leftSum = 0;

        for (int x : nums) {
            sum += x;
        }

        for (int i = 0; i < nums.length; i++) {
            if (leftSum == sum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /* 771. 宝石与石头 */
    public int numJewelsInStones(String J, String S) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char sChar : S.toCharArray()) {
            map.put(sChar, map.getOrDefault(sChar, 0) + 1);
        }
        int result = 0;
        for (char jChar : J.toCharArray()) {
            result += map.getOrDefault(jChar, 0);
        }
        return result;
    }

    public int numJewelsInStones2(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        for (char jChar : J.toCharArray()) {
            set.add(jChar);
        }
        int result = 0;
        for (char sChar : S.toCharArray()) {
            if (set.contains(sChar)) {
                result += 1;
            }
        }
        return result;
    }

    public int numJewelsInStones3(String J, String S) {
        int result = 0;
        for (char jChar : J.toCharArray()) {
            for (char sChar : S.toCharArray()) {
                if (jChar == sChar) {
                    result += 1;
                }
            }
        }
        return result;
    }
}
