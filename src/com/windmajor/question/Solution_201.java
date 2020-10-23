package com.windmajor.question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution_201 {

    /* 204. 计数质数 */
    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);

        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }

        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) ans += 1;
        }
        return ans;
    }

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

    /* 268. 丢失的数字 */
    public int missingNumber(int[] nums) { // 数学方法
        int count1 = 0;
        for (int num : nums) {
            count1 += num;
        }

        int count2 = 0;
        for (int i = 0; i < nums.length + 1; i++) {
            count2 += i;
        }

        return count2 - count1;
    }

    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);

        if (nums[0] != 0) {
            return 0;
        }

        if (nums[nums.length - 1] != nums.length) {
            return nums.length;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return -1;
    }

    public int missingNumber3(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    public int missingNumber4(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    /* 278. 第一个错误的版本 */
    private boolean isBadVersion(int version) {
        return true;
    }

    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
