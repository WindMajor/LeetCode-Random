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

    /* 9. 回文数 */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int rev = 0;
        while (x > rev) { // 只需要判断弹出了一半的数字进行对比即可
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (x == rev || x == rev / 10);
    }

    public boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int value = x;
        int rev = 0;

        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            rev = rev * 10 + pop;
        }

        return rev == value;
    }

    /* 13. 罗马数字转整数 */
    public int romanToInt(String s) {
        char[] array = s.toCharArray();
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                if (getValue(array[i]) >= getValue(array[i + 1])) {
                    result += getValue(array[i]);
                } else {
                    result -= getValue(array[i]);
                }
            } else {
                result += getValue(array[i]);
            }
        }
        return result;
    }

    public int romanToInt2(String s) {
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

    /* 14. 最长公共前缀 */

    // 分治策略
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestPrefix(strs, 0, strs.length - 1);
    }

    private String longestPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String left = longestPrefix(strs, start, mid);
            String right = longestPrefix(strs, mid + 1, end);
            return commonPrefix(left, right);
        }
    }

    private String commonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minLength);
    }

    // 两分法 这里并不是典型的两分法，因为牵扯到一个接口 sbustring(string, end)，实际上end是取不到的，包左不包右，这里要做特殊的处理
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }

        int low = 0;
        int high = minLength;
        int ans = 0;
        while (low < high) {
            int mid = (low + high) / 2;
            if (isCommonPrefix(strs, mid + 1)) { // 这里需要特殊处理
                low = mid + 1; // 这里也是特殊处理了
            } else {
                high = mid;
            }
        }
        return strs[0].substring(0, low);
    }

    private boolean isCommonPrefix(String[] strs, int index) {
        String str0 = strs[0].substring(0, index);
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < str0.length(); j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 纵向扫描
    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < strs[0].length(); i++) {
            char currentChar = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || currentChar != strs[j].charAt(i)) {
                    return ans.toString();
                }
            }
            ans.append(currentChar);
        }
        return ans.toString();
    }

    // 横向扫描
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            ans = getPrefix(ans, strs[i]);
            if (ans.length() == 0) {
                break;
            }
        }
        return ans;
    }

    private String getPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index += 1;
        }
        return str1.substring(0, index);
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
