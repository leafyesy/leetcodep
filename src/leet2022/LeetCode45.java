package src.leet2022;

public class LeetCode45 {

    public static void main(String[] args) {
        int jump = new LeetCode45().jump(new int[]{2,3,0,1,4});
//        int jump = new LeetCode45().jump(new int[]{10,9,8,7,6,5,4,3,2,1,1,0});
        System.out.println("jumo:" + jump);
    }

    public int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int jumpTimes = 0;
        int curIndex = 0;
        while (true) {
            int num = nums[curIndex];
            if (curIndex + num >= nums.length - 1) {
                return jumpTimes + 1;
            }
            int maxNum = 0;
            int maxNumInddex = 0;
            for (int i = 1; i <= num; i++) {
                //找到最大值
                int nextNum = nums[curIndex + i] + curIndex + i; // 每次都是找能跳转到最远的位置
                if (nextNum >= maxNum) {
                    maxNum = nextNum;
                    maxNumInddex = curIndex + i;
                }
            }
            curIndex = maxNumInddex;
            jumpTimes++;
        }
    }

}
