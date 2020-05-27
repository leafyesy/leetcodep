package src.leetcode100;

import src.base.TreeNode;
import src.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树中序打印
 */
public class Leetcode94 {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, null, 2, null, null, 3};
        TreeNode root = TreeNode.create(arr);
        List<Integer> integers = Solution.inorderTraversal3(root);
        List<Integer> list = new ArrayList<>();
        Solution.inorderTraversalDigui(root, list);
        Utils.printList(integers);
        Utils.printList(list);
    }


    static class Solution {
        public static List<Integer> inorderTraversal(TreeNode root) {
            return inorderTraversalIn(root);
        }

        //递归
        public static void inorderTraversalDigui(TreeNode root, List<Integer> list) {
            if (root.left != null) inorderTraversalDigui(root.left, list);
            list.add(root.val);
            if (root.right != null) inorderTraversalDigui(root.right, list);
        }

        public static List<Integer> inorderTraversalIn(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            List<Integer> list = new ArrayList<>();
            TreeNode cur = root;
            while (cur != null) {
                System.out.println("cur:" + cur.val + " cur.left:" + (cur.left != null ? cur.left.val : -1) + " cur.right:" + (cur.right != null ? cur.right.val : -1));
                while (cur.left != null) {
                    stack.add(cur);
                    cur = cur.left;
                }
                //left == null
                list.add(cur.val);
                if (cur.right != null) {
                    cur = cur.right;
                    continue;
                }
                //没有右边
                cur = null;
                while (!stack.empty()) {
                    cur = stack.pop();
                    list.add(cur.val);//中间
                    if (cur.right != null) {
                        cur = cur.right;
                        break;
                    } else if (stack.empty()) {
                        cur = null;
                        break;
                    }
                }
                if (cur == null) break;
            }
            return list;
        }

        public static List<Integer> inorderTraversalIn2(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            List<Integer> list = new ArrayList<>();
            TreeNode cur = root;
            while (cur != null || !stack.empty()) {
                while (cur != null) {
                    stack.add(cur);
                    cur = cur.left;
                }
                //left == null
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
            return list;
        }

        public static List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> ldr = new ArrayList<Integer>();
            TreeNode cur = root;
            TreeNode pre = null;
            while (cur != null) {
                if (cur.left == null) {//左子树为空，输出当前节点，将其右孩子作为当前节点
                    ldr.add(cur.val);
                    cur = cur.right;
                } else {
                    pre = cur.left;//左子树
                    while (pre.right != null && pre.right != cur) {//找到前驱节点，即左子树中的最右节点
                        pre = pre.right;
                    }
                    //如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
                    if (pre.right == null) {
                        pre.right = cur;
                        cur = cur.left;
                    }
                    //如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
                    if (pre.right == cur) {
                        pre.right = null;
                        ldr.add(cur.val);
                        cur = cur.right;
                    }
                }
            }
            return ldr;
        }

        public static List<Integer> mls(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            TreeNode pre = null;
            TreeNode cur = root;
            while (cur != null) {
                System.out.println("cur.val:" + cur.val);
                if (cur.left != null) {
                    pre = cur.left;
                    while (pre.right != null && pre.right != cur) {
                        pre = pre.right;
                    }
                    if (pre.right == null) {
                        pre.right = cur;
                        cur = cur.left;
                    }
                    if (pre.right == cur) {
                        pre.right = null;
                        result.add(cur.val);
                        cur = cur.right;
                    }
                } else {
                    result.add(cur.val);
                    cur = cur.right;
                }
            }
            return result;
        }

    }

}
