package com.windmajor.question;

import java.util.*;

public class Solution_101 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /* 101. 对称二叉树 */
    // 递归深度优先
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compareTreeNode(root.left, root.right);
    }

    private boolean compareTreeNode(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        } else if (l == null ^ r == null) {
            return false;
        } else if (l.val != r.val) {
            return false;
        } else {
            return compareTreeNode(l.left, r.right) && compareTreeNode(l.right, r.left);
        }
    }

    // 迭代广度优先
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);
        while (!q.isEmpty()) {
            TreeNode l = q.poll();
            TreeNode r = q.poll();
            if (l == null && r == null) {
                continue;
            } else if (l == null ^ r == null) {
                return false;
            } else if (l.val != r.val) {
                return false;
            }
            q.offer(l.left);
            q.offer(r.right);
            q.offer(l.right);
            q.offer(r.left);
        }
        return true;
    }

    /* 104. 二叉树的最大深度 */
    // 递归 深度优先
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 迭代 广度优先
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                size -= 1;
            }
            ans += 1;
        }
        return ans;
    }

    /* 107. 二叉树的层序遍历 II */
    // 深度优先
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode node, int depth, List<List<Integer>> ans) {
        if (depth == ans.size()) {
            ans.add(0, new ArrayList<>());
        }
        ans.get(ans.size() - 1 - depth).add(node.val);

        if (node.left != null) {
            dfs(node.left, depth + 1, ans);
        }
        if (node.right != null) {
            dfs(node.right, depth + 1, ans);
        }
    }

    // BFS 广度优先
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            while (size > 0) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                size -= 1;
            }
            ans.add(0, list);
        }
        return ans;
    }

    /* 108. 将有序数组转换为二叉搜索树 */

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

    /* 110.平衡二叉树 */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        }

        return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(height(root.left) - height(root.right)) <= 1);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /* 111.二叉树的最小深度 */
    // 广度优先 耗时：1ms
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ans += 1;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) {
                    return ans;
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }

    // 深度优先 耗时：10ms
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int minHeight = Integer.MAX_VALUE;
        if (root.left != null) {
            minHeight = Math.min(minDepth2(root.left), minHeight);
        }
        if (root.right != null) {
            minHeight = Math.min(minDepth2(root.right), minHeight);
        }
        return 1 + minHeight;
    }

    /* 112.路径综合 */
    // DFS
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // BFS
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int value = valQueue.poll();
            if (node.left == null && node.right == null) {
                if (value == targetSum) {
                    return true;
                }
                continue;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(node.left.val + value);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(node.right.val + value);
            }
        }
        return false;
    }


    /* 118. 杨辉三角 */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    List<Integer> oldList = ans.get(i - 1);
                    list.add(j, oldList.get(j - 1) + oldList.get(j));
                }
            }
            ans.add(list);
        }
        return ans;
    }

    /* 119. 杨辉三角II */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = 0; j < i; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
            list.add(0, 1);
        }
        return list;
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

    /* 136. 只出现一次的数字 */
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    /* 141. 环形链表 */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /* 155. 最小栈 */
    class MinStack {

        class Node {
            int val;
            int min;
            Node next;

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
            }

            public Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }

        private Node head;

        public MinStack() { }

        public void push(int x) {
            if (head == null) {
                head = new Node(x, x);
            } else {
                head = new Node(x, Math.min(x, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    static class MinStack2 {

        Deque<int[]> stack;

        /**
         * initialize your data structure here.
         */
        public MinStack2() {
            stack = new LinkedList<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(new int[]{x, x});
            } else {
                stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }

    static class MinStack3 {
        LinkedList<Integer> stack = new LinkedList<>();
        LinkedList<Integer> minStack = new LinkedList<>();

        public MinStack3() {
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

    /* 160. 相交链表 */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != nodeB) {
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeA = nodeA.next;
            }
            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = nodeB.next;
            }
        }
        return nodeA;
    }

    /* 167. 两数之和 II */
    // 双指针
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[2];
        }
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            if (target == numbers[l] + numbers[r]) {
                return new int[] {l + 1, r + 1};
            } else if (target > numbers[l] + numbers[r]) {
                l += 1;
            } else {
                r -= 1;
            }
        }
        return new int[2];
    }
    // 两分法
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[2];
        }
        for (int i = 0; i < numbers.length; i++) {
            int l = i + 1;
            int h = numbers.length - 1;
            while (l <= h) {
                int mid = (l + h) / 2;
                if (target == numbers[i] + numbers[mid]) {
                    return new int[]{i + 1, mid + 1};
                } else if (target > numbers[i] + numbers[mid]) {
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
        }
        return new int[2];
    }

    /* 168. Excel表列名称*/
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int pop = (n - 1) % 26;
            n = (n - 1) / 26;
            sb.append((char)('A' + pop));
        }
        return sb.reverse().toString();
    }

    /* 169. 多数元素 */
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count += 1;
            } else {
                count -= 1;
            }
        }
        return candidate;
    }

    /* 171. Excel表列序号 */
    public int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + value;
        }
        return ans;
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
