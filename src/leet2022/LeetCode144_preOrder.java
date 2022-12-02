package src.leet2022;

import src.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode144_preOrder {


    public List<Integer> preOrderTraversal(TreeNode node) {
        List<Integer> result = new ArrayList<Integer>();
        innerPreOrder(node, result);
        return result;
    }

    private void innerPreOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        innerPreOrder(node.left, result);
        innerPreOrder(node.right, result);
    }




    public List<Integer> preOrderTraversal2(TreeNode node) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return result;
    }


}
