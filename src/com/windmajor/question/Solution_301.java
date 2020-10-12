package com.windmajor.question;

import java.util.*;

public class Solution_301 {

    /* 326. 3的幂 */
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public boolean isPowerOfThree2(int n) {
        return Integer.toString(n, 3) // 第一步是转换成3进制的数字字符串
                .matches("^10*$"); // 第二步判断是否符合1后面都是0的特征
    }

    public boolean isPowerOfThree3(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    public boolean isPowerOfThree4(int n) {
        // n 是 int 类型的，在int值的范围内，3的幂的最大值是 3的19次方 = 1162261467
        return n > 0 && 1162261467 % n == 0;
    }

    /* 347. 前 K 个高频元素 */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[0];
        }
        return ans;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[]{entry.getKey(), entry.getValue()});
        }

        int[] ans = new int[k];
        quickSort(list, 0, list.size() - 1, ans, 0, k);
        return ans;
    }

    public void quickSort(List<int[]> list, int start, int end, int[] ans, int ansIndex, int k) {
        int pivot = list.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (list.get(i)[1] >= pivot) {
                index += 1;
                Collections.swap(list, index, i);
            }
        }
        Collections.swap(list, index, start);

        if (k <= index - start) {
            quickSort(list, start, index - 1, ans, ansIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ans[ansIndex] = list.get(i)[0];
                ansIndex += 1;
            }
            if (k > index - start + 1) {
                quickSort(list, index + 1, end, ans, ansIndex, k - (index - start + 1));
            }
        }
    }


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

    /* 380. 常数时间插入、删除和获取随机元素 */
    public static class RandomizedSet {
        Map<Integer, Integer> dict;
        List<Integer> list;
        Random rand = new Random();

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            dict = new HashMap<>();
            list = new ArrayList<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (dict.containsKey(val)) return false;

            list.add(list.size(), val);
            dict.put(val, list.size() - 1);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!dict.containsKey(val)) return false;

            int removeIndex = dict.get(val);
            int lastElement = list.get(list.size() - 1);

            list.set(removeIndex, lastElement);
            dict.put(lastElement, removeIndex);

            list.remove(list.size() - 1);
            dict.remove(val);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }

    /* 384. 打乱数组 */
    static class Solution1 {
        private int[] array;
        private int[] original;

        private Random rand = new Random();

        public Solution1(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] shuffle() {
            List<Integer> asList = new ArrayList<>();
            for (int value : array) {
                asList.add(value);
            }

            for (int i = 0; i < array.length; i++) {
                int removeIdx = rand.nextInt(asList.size());
                array[i] = asList.get(removeIdx);
                asList.remove(removeIdx);
            }
            return array;
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return array;
        }
    }

    static class Solution2 {
        private int[] array;
        private int[] original;

        Random rand = new Random();

        public Solution2(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return original;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }

        private int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
//            array[i] = array[i] ^ array[j];
//            array[j] = array[i] ^ array[j];
//            array[i] = array[i] ^ array[j]; 这样不行
        }
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
