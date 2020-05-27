package src.leetcode100;

import src.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Leetcode96 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.nodeCodeCal(19);
        System.out.println("result:" + i);
    }


    static class Solution {

        private HashMap<Key, List<TreeNode>> hashMap = new HashMap<>();

        private HashMap<Integer, Integer> countMap = new HashMap<>();

        public int nodeCodeCal(int n) {
            Integer value = countMap.get(n);
            if (value != null) return value;
            if (n == 0) {
                countMap.put(0, 1);
                return 1;
            }
            if (n == 1) {
                countMap.put(1, 1);
                return 1;
            }
            int count = 0;
            for (int i = 1; i <= n; i++) {
                int left = nodeCodeCal(i - 1);
                int right = nodeCodeCal(n - i);
                count += left * right;
            }
            countMap.put(n, count);
            return count;
        }

        public int numTrees(int n) {
            List<TreeNode> list = new ArrayList<>(generateNodeList(1, n));
            //printTreeNodeList(list);
            return list.size();
        }

        private void printTreeNodeList(List<TreeNode> list) {
            for (TreeNode treeNode : list) {
                printTreeNode(treeNode);
            }
        }

        private void printTreeNode(TreeNode node) {
            List<TreeNode> list = new ArrayList<>();
            list.add(node);
            int index = 0;
            System.out.print("[");
            while (index < list.size()) {
                int size = list.size();
                while (index < size) {
                    System.out.print(list.get(index).val + ",");
                    if (list.get(index).left != null) {
                        list.add(list.get(index).left);
                    }
                    if (list.get(index).right != null) {
                        list.add(list.get(index).right);
                    }
                    index++;
                }
                System.out.print("---");
            }
            System.out.println("]");
        }


        static class Key {
            int left;
            int right;

            public Key(int left, int right) {
                this.left = left;
                this.right = right;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Key key = (Key) o;
                return left == key.left &&
                        right == key.right;
            }

            @Override
            public int hashCode() {
                return Objects.hash(left, right);
            }
        }

        TreeNode nullNode = new TreeNode(-1);

        public List<TreeNode> generateNodeList(int left, int right) {
            Key k = new Key(left, right);
            List<TreeNode> value = hashMap.get(k);
            if (value != null) return value;
            //System.out.println("leftL:" + left + " rightL:" + right);
            List<TreeNode> list = new ArrayList<>();
            if (left == right) {
                list.add(new TreeNode(left));
            } else if (left > right) {
                list.add(nullNode);
            } else {
                for (int i = left; i <= right; i++) {
                    List<TreeNode> leftList = generateNodeList(left, i - 1);
                    List<TreeNode> rightList = generateNodeList(i + 1, right);
                    for (TreeNode leftNode : leftList) {
                        for (TreeNode rightNode : rightList) {
                            TreeNode root = new TreeNode(i);
                            root.left = leftNode;
                            root.right = rightNode;
                            list.add(root);
                        }
                    }
                }
            }
            hashMap.put(k, list);
            return list;
        }
    }

}
