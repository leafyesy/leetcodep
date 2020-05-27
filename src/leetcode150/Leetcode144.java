package src.leetcode150;

import src.base.TreeNode;
import src.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode144 {

    //二叉树前序打印

    public static void main(String[] args) {
        TreeNode node = TreeNode.create(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        List<Integer> list = preorderTraversal(node);
        Utils.printList(list);

        List<Integer> list1 = preorderTraversal1(node);
        Utils.printList(list1);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        proorderTraversalDigui(root, list);
        return list;
    }

    public static void proorderTraversalDigui(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null)
            proorderTraversalDigui(root.left, list);
        if (root.right != null)
            proorderTraversalDigui(root.right, list);
    }

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            list.add(cur.val);
            stack.add(cur);
            cur = cur.left;
            while (cur == null && !stack.empty()) {
                cur = stack.pop().right;
            }
        }
        return list;
    }

    public static List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                list.add(cur.val);
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return list;
    }

    /*
    中序
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
     */


}
