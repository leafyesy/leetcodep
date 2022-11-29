package src.leet2022;

import src.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//中序遍历:左子树---> 根结点 ---> 右子树
public class LeetCode94 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        List<Integer> list = new LeetCode94().inorderTraversal2(node1);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        appendNode(root, result);
        return result;
    }

    // 递归方法
    private void appendNode(TreeNode head, List<Integer> result) {
        if (head == null) {
            return;
        }
        appendNode(head.left, result);
        result.add(head.val);
        appendNode(head.right, result);
    }

    // 一次入栈,会破坏原节点数据
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            List<TreeNode> cacheTreeNode = new ArrayList<>();
            TreeNode cur = root;
            do {
                if (cur != null) {// left不为空,就一直入栈
                    cacheTreeNode.add(cur);
                    cur = cur.left;
                } else {
                    cur = cacheTreeNode.remove(cacheTreeNode.size() - 1);
                    result.add(cur.val);
                    cur = cur.right;
                }
            } while (cur != null || !cacheTreeNode.isEmpty());
        }
        return result;
    }


    public List<Integer> inOrderTraverse2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                result.add(node.val);
                pNode = node.right;
            }
        }
        return result;
    }


    // 两次入栈,代码容易理解
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNodeFlag> stack = new Stack<>();
        stack.add(new TreeNodeFlag(root, false));
        while (!stack.isEmpty()) {
            TreeNodeFlag tailNodeFlag = stack.pop();
            if (!tailNodeFlag.isRead) {
                if (tailNodeFlag.node.right != null) {
                    stack.push(new TreeNodeFlag(tailNodeFlag.node.right, false));
                }
                stack.push(new TreeNodeFlag(tailNodeFlag.node, true));
                if (tailNodeFlag.node.left != null) {
                    stack.push(new TreeNodeFlag(tailNodeFlag.node.left, false));
                }
            } else {
                result.add(tailNodeFlag.node.val);
            }
        }
        return result;
    }

    private static class TreeNodeFlag {

        private final TreeNode node;
        private final boolean isRead;

        public TreeNodeFlag(TreeNode node, boolean isRead) {
            this.node = node;
            this.isRead = isRead;
        }
    }


}
