package src.interview;

import src.base.TreeNode;

import java.util.Stack;

public class InterviewFindXTreeNode {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                1, -1, 3, -2
        };
        TreeNode treeNode = TreeNode.create(arr);
        Solution solution = new Solution();
        int result = solution.findX(treeNode, 3);
        System.out.println(result);
    }


    static class Solution {

        public int findX(TreeNode root, int x) {
            Stack<TreeNode> stack = new Stack<>();
            Count count = new Count(x);
            return findX(root, count, stack);
        }

        class Count {
            Count(int x) {
                this.x = x;
            }

            int x;
        }

        private int findX(TreeNode root, Count count, Stack<TreeNode> stack) {
            TreeNode cur = root;
            while (cur != null) {
                System.out.println("cur:"+cur.val);
                stack.push(cur);
                cur = cur.right;
            }
            while (count.x >= 1 && !stack.isEmpty()) {
                TreeNode pop = stack.pop();
                if (count.x <= 1) {
                    return pop.val;
                }
                count.x--;
                if (pop.left != null) {
                    return findX(pop.left, count, stack);
                }
            }
            return -1;
        }


    }
}
