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

    /* 7. 整数反转 */
    //  Integer.MAX_VALUE = 2147483647
    //  Integer.MIN_VALUE = -2147483648
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /* 13. 罗马数字转整数 */
    public int romanToInt(String s) {
        int ans = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                ans -= preNum;
            } else {
                ans += preNum;
            }
            preNum = num;
        }
        ans += preNum;
        return ans;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /* 20. 有效的括号 */
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();

            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
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

    /* 53. 最大子序和 */
    public int maxSubArray(int[] nums) { // 贪心算法
        int pre = 0;
        int maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public int maxSubArray2(int[] nums) {  // 动态规划
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 < 0) continue;
            if (nums[i - 1] > 0) {
                nums[i] = nums[i] + nums[i - 1];
            }
        }

        int ans = nums[0];
        for (int num : nums) {
            ans = Math.max(ans, num);
        }
        return ans;
    }


    /* 70. 爬楼梯 */
    public int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
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
