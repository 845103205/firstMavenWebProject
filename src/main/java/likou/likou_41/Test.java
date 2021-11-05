package likou.likou_41;

public class Test {
    public static void main(String[] args) {
        //System.out.println(Test.firstMissingPositive(new int[]{1,2,3,0,5,66666}));
        System.out.println(Test.firstMissingPositive1(new int[]{1,2,3,0,5,66666}));
    }
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        /*把所有负数放到n+1的位置上*/
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        /**/
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

}
