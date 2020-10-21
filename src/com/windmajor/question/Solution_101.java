package com.windmajor.question;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution_101 {


    /* 108. 将有序数组转换为二叉搜索树 */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2; // 中序遍历，总是选择中间位置左边的数字作为根节点
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper2(nums, 0, nums.length - 1);
    }

    public TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right + 1) / 2; // 中序遍历，总是选择中间位置右边的数字作为根节点
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /* 118. 杨辉三角 */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }

        ans.add(new ArrayList<>());
        ans.get(0).add(1);
        if (numRows == 1) {
            return ans;
        }

        ans.add(new ArrayList<>());
        ans.get(1).add(1);
        ans.get(1).add(1);
        if (numRows == 2) {
            return ans;
        }

        for (int i = 2; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = ans.get(i - 1);

            row.add(1);

            for (int j = 1; j < i; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }

            row.add(1);
            ans.add(row);
        }
        return ans;
    }

    /* 121. 买卖股票的最佳时机 */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int ans = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                ans = Math.max(ans, prices[i] - prices[j]);
            }
        }
        return ans;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;

        int lowPrice = prices[0];
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans = Math.max(ans, prices[i] - lowPrice);
            lowPrice = Math.min(lowPrice, prices[i]);
        }
        return ans;
    }

    /* 155. 最小栈 */
    static class MinStack {
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> minStack = new LinkedList<>();

        public MinStack() {
            minStack.push(Integer.MAX_VALUE); // 预存一个最大值，用来作比较
        }

        public void push(int x) {
            stack.push(x);
            minStack.push(Math.min(minStack.peek(), x));  // 这个minStack每次都只存最小值，可能存在多个一样的值，长度比stack的长度多1
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /* 190. 颠倒二进制位 */
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans = (ans << 1) + (n & 1);
            n >>= 1;
        }
        return ans;
    }

    public int reverseBits2(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans ^= ((i << i) & n) != 0 ? (1 << (31 - i)) : 0;
        }
        return ans;
    }

    public int reverseBits3(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    /* 191. 位1的个数 */
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }

    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    /* 198. 打家劫舍 */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
