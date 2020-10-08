package com.windmajor.question;

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
}
