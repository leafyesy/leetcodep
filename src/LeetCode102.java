import java.util.ArrayList;
import java.util.List;

public class LeetCode102 {

    public static void main(String[] args) {
        TreeNode headNode = new TreeNode(0);
        TreeNode secondLeft = new TreeNode(1);
        TreeNode secondRight = new TreeNode(2);
        TreeNode treeLeft1 = new TreeNode(3);
        TreeNode treeRight1 = new TreeNode(4);
        TreeNode treeLeft2 = new TreeNode(5);
        TreeNode treeRight2 = new TreeNode(6);
        headNode.leftNode = secondLeft;
        headNode.rightNode = secondRight;
        //secondLeft.leftNode = treeLeft1;
        secondLeft.rightNode = treeRight1;
        secondRight.leftNode = treeLeft2;
        secondRight.rightNode = treeRight2;
        Solution solution = new Solution();
        List<TreeNode> treeNodes = solution.printTreeNode(headNode);
        for (TreeNode treeNode : treeNodes) {
            System.out.println("value:" + treeNode.value);
        }
    }

    static class TreeNode {

        public TreeNode(int value) {
            this.value = value;
        }

        int value;

        TreeNode leftNode;
        TreeNode rightNode;

    }

    static class Solution {

        int index = -1;

        public List<TreeNode> printTreeNode(TreeNode node) {
            List<TreeNode> nodeList = new ArrayList<>();
            nodeList.add(node);
            index = 0;
            sort(nodeList);
            return nodeList;
        }

        private void sort(List<TreeNode> nodeList) {
            int preSize = nodeList.size();
            for (int i = index; i < preSize; i++) {
                TreeNode treeNode = nodeList.get(i);
                if (treeNode.leftNode != null) {
                    nodeList.add(treeNode.leftNode);
                }
                if (treeNode.rightNode != null) {
                    nodeList.add(treeNode.rightNode);
                }
            }
            index = preSize;
            int newSize = nodeList.size();
            if (newSize > preSize) {
                sort(nodeList);
            }
        }

    }

}
