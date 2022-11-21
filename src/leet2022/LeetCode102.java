package src.leet2022;

import src.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode102 {

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            if (root == null) {
                return result;
            }
            List<TreeNode> nodeList = new ArrayList<TreeNode>();
            nodeList.add(root);
            int left = 0;
            int right = 1;
            while (true) {
                List<Integer> list = new ArrayList<Integer>();
                while (left < right) {
                    TreeNode treeNode = nodeList.get(left);
                    list.add(treeNode.val);
                    if (treeNode.left != null) {
                        nodeList.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        nodeList.add(treeNode.right);
                    }
                    left++;
                }
                if (!list.isEmpty()) {
                    result.add(list);
                    right = nodeList.size();
                } else {
                    break;
                }
            }
            return result;
        }
    }


}
