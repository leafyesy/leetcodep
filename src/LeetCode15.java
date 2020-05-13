import java.util.*;

public class LeetCode15 {

    public static void main(String[] args) {
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums = new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
//        int[] nums = new int[]{0, 0, 0};
        Solution2 s = new Solution2();
        List<List<Integer>> lists = s.threeSum(nums);
        System.out.println(lists);
    }

    static class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            if (nums == null || nums.length < 3) return list;
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0) {
                    if (nums[i - 1] == nums[i]) {
                        continue;
                    }
                }
                if (nums[i] > 0) break;
                int left = i + 1;
                int right = nums.length - 1;
                for (; left < right; ) {
                    System.out.println("left:" + left + "  right:" + right);
                    int leftPre = left - 1;
                    if (leftPre != i && nums[leftPre] == nums[left]) {
                        left++;
                        continue;
                    }
                    int rightPre = right + 1;
                    if (right < nums.length - 1 && nums[rightPre] == nums[right]) {
                        right--;
                        continue;
                    }
                    int result = nums[i] + nums[left] + nums[right];
                    if (result == 0) {
                        list.add(createInnerList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;
                    } else if (result > 0) {
                        right--;
                    } else {
                        left++;
                    }

                }
            }
            return list;
        }
        private static List<Integer> createInnerList(int f, int s, int t) {
            List<Integer> innerList = new ArrayList<>();
            innerList.add(f);
            innerList.add(s);
            innerList.add(t);
            return innerList;
        }
    }


//    static class Solution2 {
//        public List<List<Integer>> threeSum(int[] nums) {
//            List<List<Integer>> resultList = new ArrayList<>();
//            if (nums == null || nums.length < 3) return resultList;
//            Arrays.sort(nums);
//            HashMap<Integer, Entry> hashMap = new HashMap<>();
//            for (int i = 0; i < nums.length; i++) {
//                for (int j = nums.length - 1; j > i; j--) {
//                    Entry entry = hashMap.get(nums[i] + nums[j]);
//                    if (entry == null) {
//                        entry = new Entry();
//                    }
//                    entry.add(i, j);
//                    hashMap.put(nums[i] + nums[j], entry);
//                }
//            }
//            for (int index = 0; index < nums.length; index++) {
//                Entry entry = hashMap.get(-nums[index]);
//                if (entry != null&&entry.nodeList!=null) {
//                    for (Entry.Node node : entry.nodeList) {
//                        if (!node.isConflic(index)){
//
//                        }
//                    }
//                }
//            }
//        }
//    }


    static class Entry {

        List<Node> nodeList;

        void add(int f, int s) {
            Node n = new Node(f, s);
            if (nodeList == null) {
                nodeList = new ArrayList<>();
            }
            if (!nodeList.contains(n))
                nodeList.add(n);
        }


        static class Node {
            public Node(int first, int second) {
                this.first = first;
                this.second = second;

            }

            int first;
            int second;

            private List<Node> indexList;

            boolean isConflic(int index) {
                return index == first || index == second;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return (first == node.first &&
                        second == node.second);
            }

            @Override
            public int hashCode() {
                return Objects.hash(first, second);
            }
        }

    }

    /**
     * 各种边界调节的判断
     */
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> resultList = new ArrayList<>();
            if (nums == null || nums.length < 3) return resultList;
            Arrays.sort(nums);
            int zeroCount = 0;//判断0的个数
            boolean isCanuse;//跳过相同数检查
            for (int index = 1; index < nums.length - 1; index++) {
                isCanuse = false;
                int i = 0;
                int j = nums.length - 1;
                if (nums[index] == 0)
                    zeroCount++;
                int pre = nums[index - 1];
                int behind = nums[index + 1];
                if (nums[index] == pre) {
                    if (nums[index] == behind) {
                        if (nums[index] == 0) {
                            zeroCount = 3;
                        }
                        continue;
                    } else {
                        i = index - 1;
                        isCanuse = true;
                    }
                }
                for (; i < index && j > index; ) {
                    if (i - 1 >= 0 && !isCanuse) {
                        if (nums[i] == nums[i - 1]) {
                            i++;
                            continue;
                        }
                    }
                    if (j + 1 <= nums.length - 1) {
                        if (nums[j] == nums[j + 1]) {
                            j--;
                            continue;
                        }
                    }
                    int diff = nums[index] + (nums[i] + nums[j]);
                    if (diff == 0) {
                        List<Integer> innerList = createInnerList(nums[i], nums[index], nums[j]);
                        resultList.add(innerList);
                        i++;
                    } else if (diff > 0) {
                        j--;
                    } else {
                        i++;
                    }
                }
            }
            if (zeroCount >= 3) {
                resultList.add(createInnerList(0, 0, 0));
            }
            return resultList;
        }
    }

    private static List<Integer> createInnerList(int f, int s, int t) {
        List<Integer> innerList = new ArrayList<>();
        innerList.add(f);
        innerList.add(s);
        innerList.add(t);
        return innerList;
    }

}
