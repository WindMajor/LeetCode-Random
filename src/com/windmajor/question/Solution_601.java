package com.windmajor.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_601 {


    /* 652. 寻找重复的子树 */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 652 第1种方法
    int id;
    HashMap<String, Integer> treesIdMap;
    HashMap<Integer, Integer> nodeCountMap;
    List<TreeNode> resultMap;

    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        id = 0;
        treesIdMap = new HashMap<>();
        nodeCountMap = new HashMap<>();
        resultMap = new ArrayList<>();
        lookup(root);
        return resultMap;
    }

    public int lookup(TreeNode node) {
        if (node.val == 0) return 0;
        String idStr = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        Integer id = treesIdMap.computeIfAbsent(idStr, x -> this.id + 1);
        nodeCountMap.put(id, nodeCountMap.getOrDefault(id, 0) + 1);
        if (nodeCountMap.get(id) == 2) {
            resultMap.add(node);
        }
        return id;
    }

    // 652 第2种解法
    HashMap<String, Integer> countMap;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        countMap = new HashMap<>();
        ans = new ArrayList<>();
        collect(root);
        return ans;
    }

    public String collect(TreeNode node) {
        if (node == null) return "#";
        String identifyStr = node.val + "," + collect(node.left) + "," + collect(node.right);
        countMap.put(identifyStr, countMap.getOrDefault(identifyStr, 0) + 1);
        if(countMap.get(identifyStr) == 2)
            ans.add(node);
        return identifyStr;
    }
}
