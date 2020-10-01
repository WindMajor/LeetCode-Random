package com.windmajor.question;

import java.util.*;

public class Solution_001 {

    /* 1.两数之和 */
    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.isEmpty()) {
                Integer integer = map.get(target - nums[i]);
                if (integer != null) {
                    return new int[]{integer, i};
                }
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }


    /* 3. 无重复字符的最长子串 */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();

        char[] chars = s.toCharArray();
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            set.add(chars[i]);
            int j = 1;
            while ((i + j) < s.length() && !set.contains(chars[i + j])) {
                set.add(chars[i + j]);
                j += 1;
            }
            result = Math.max(result, set.size());
            set.clear();
        }
        return result;
    }

    public int lengthOfLongestSubstring2(String s) {
        HashSet<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int n = chars.length;

        int k = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                set.remove(chars[i - 1]); // 每次遍历都将最左边的字符从set中移除。
            }
            // 只要不重复，尽可能的把字符放入set中；每次新的for循环，移除最左边的字符，看能不能继续不重复的向set中放字符。
            while (k < n && !set.contains(chars[k])) {
                set.add(chars[k]);
                k += 1;
            }
            result = Math.max(result, set.size());
        }
        return result;
    }


    /* 36. 有效的数独 */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> rowSet = new HashSet<>();
        HashSet<Character> columnSet = new HashSet<>();
        HashSet<Character> sudokuSet = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            rowSet.clear();
            columnSet.clear();
            sudokuSet.clear();
            for (int j = 0; j < 9; j++) {

                if (board[i][j] != '.' && !rowSet.add(board[i][j])) {
                    return false;
                }
                if (board[j][i] != '.' && !columnSet.add(board[j][i])) {
                    return false;
                }

                int rowIndex = j / 3 + 3 * (i / 3);
                int columnIndex = j % 3 + 3 * (i % 3);
                if (board[rowIndex][columnIndex] != '.' && !sudokuSet.add(board[rowIndex][columnIndex])) {
                    return false;
                }
            }
        }
        return true;
    }

    /* 49. 字母异位词分组 */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) return new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String key = String.valueOf(ch);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /* 88. 合并两个有序数组 */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        int p1 = 0;
        int p2 = 0;

        int p = 0;

        while ((p1 < m) && (p2 < n)) {
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        System.arraycopy(nums2, 0, nums2, 0, p2 + 1);
    }
}
