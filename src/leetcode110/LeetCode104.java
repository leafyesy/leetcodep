package src.leetcode110;

import src.base.TreeNode;

//二叉树最大深度
public class LeetCode104 {

    class Solution {

        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }


        // public int maxDepth(TreeNode root) {
        //     if(root == null) return 0;
        //     List<TreeNode> treeNodeList = new ArrayList<>();
        //     treeNodeList.add(root);
        //     int last = 0;//最好一位的位置
        //     int count = 1;//计数
        //     int curIndex = 0;//当前指针
        //     while(true){
        //         for(;curIndex<=last;curIndex++){
        //             TreeNode node = treeNodeList.get(curIndex);
        //             if(node.left!=null) treeNodeList.add(node.left);
        //             if(node.right!=null) treeNodeList.add(node.right);
        //         }
        //         if(last<treeNodeList.size()-1){//还有新的元素
        //             last = treeNodeList.size()-1;
        //         }else{//没有新元素添加进来了
        //             break;
        //         }
        //         count++;
        //     }
        //     return count;
        // }
    }

}
