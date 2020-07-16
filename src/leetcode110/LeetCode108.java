package src.leetcode110;

import src.base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode108 {

    public static void main(String[] args) {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBSTHelper(nums, left, mid);
            root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
            return root;
        }
        return null;
    }

    /**
     * 1.递归调用的参数有 left/right位置信息
     * 2.递归调用的隐式参数有root节点
     * //改成非递归的方法
     * 1.要保存位置信息的置换
     * 2.能找到原节点
     *
     * @param nums
     * @return
     */
    //使用递推的方式
    private TreeNode sortedArrayToBSTHelper2(int[] nums) {
        int length = nums.length;
        int arrMid = (length - 1) / 2;
        TreeNode root = new TreeNode(nums[arrMid]);
        Deque<TreeInfo> deque = new ArrayDeque<>();
        TreeInfo rootInfo = new TreeInfo(0, length - 1, arrMid, root);
        deque.add(rootInfo);
        while (!deque.isEmpty()) {
            TreeInfo pop = deque.pop();
            int left = pop.left;
            int right = pop.right;
            int innerMid = pop.mid;
            if (left == innerMid && right == innerMid) {
                //表示没有子节点了
                continue;
            }
            TreeNode node = pop.node;
            if (left < innerMid) {
                int leftMid = (left + innerMid - 1) / 2;
                TreeNode leftNode = new TreeNode(nums[leftMid]);
                node.left = leftNode;
                TreeInfo leftInfo = new TreeInfo(left, innerMid - 1, leftMid, leftNode);
                deque.add(leftInfo);
            }
            if (innerMid < right) {
                int rightMid = (innerMid + 1 + right) / 2;
                TreeNode rightNode = new TreeNode(nums[rightMid]);
                node.right = rightNode;
                TreeInfo rightInfo = new TreeInfo(innerMid + 1, right, rightMid, rightNode);
                deque.add(rightInfo);
            }
        }
        return root;
    }


    private TreeNode sortedArrayToBSTHelper3(int[] nums) {
        int length = nums.length;
        int arrMid = length / 2;
        TreeNode root = new TreeNode(nums[arrMid]);
        Deque<TreeInfo> deque = new ArrayDeque<>();
        TreeInfo rootInfo = new TreeInfo(0, length, arrMid, root);
        deque.add(rootInfo);
        while (!deque.isEmpty()) {
            TreeInfo pop = deque.pop();
            int left = pop.left;
            int right = pop.right;
            int innerMid = pop.mid;
            if (left == innerMid && right - 1 <= innerMid) {
                //表示没有子节点了
                continue;
            }
            TreeNode node = pop.node;
            if (left < innerMid) {
                int leftMid = (left + innerMid) / 2;
                TreeNode leftNode = new TreeNode(nums[leftMid]);
                node.left = leftNode;
                TreeInfo leftInfo = new TreeInfo(left, innerMid, leftMid, leftNode);
                deque.add(leftInfo);
            }
            if (innerMid < right - 1) {
                int rightMid = (innerMid + 1 + right) / 2;
                TreeNode rightNode = new TreeNode(nums[rightMid]);
                node.right = rightNode;
                TreeInfo rightInfo = new TreeInfo(innerMid + 1, right, rightMid, rightNode);
                deque.add(rightInfo);
            }
        }
        return root;
    }

    private class TreeInfo {
        int left;
        int right;
        int mid;
        TreeNode node;

        public TreeInfo(int left, int right, int mid, TreeNode node) {
            this.left = left;
            this.right = right;
            this.mid = mid;
            this.node = node;
        }
    }

    //二分法的非递归写法
    private int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + right;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


}
