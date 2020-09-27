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
}
