package src.base;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public void printSelf() {

    }


    public static TreeNode NULL_NODE = new TreeNode(-1);

    public static TreeNode create(Integer[] nodeArr) {
        if (nodeArr.length == 0) return null;
        if (nodeArr[0] == null) return null;
        List<TreeNode> list = new ArrayList<>();
        TreeNode root = null;
        for (Integer integer : nodeArr) {
            if (list.isEmpty()) {
                if (integer == null) throw new IllegalStateException("头节点不能为null");
                TreeNode node = new TreeNode(integer);
                root = node;
                list.add(node);
            } else {
                int index = (list.size() - 1) / 2;
                boolean isLeft = ((list.size() & 1) == 1);
                System.out.println("index:" + index + " isLeft:" + isLeft + " int:" + integer);
                TreeNode node = list.get(index);
                TreeNode next;
                if (integer == null)
                    next = null;
                else
                    next = new TreeNode(integer);
                if (isLeft) {
                    node.left = next;
                } else {
                    node.right = next;
                }
                if (null == next) {
                    list.add(NULL_NODE);
                } else {
                    list.add(next);
                }
            }
        }
        return root;
    }

}
