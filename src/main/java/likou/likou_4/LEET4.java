package likou.likou_4;

import java.util.Arrays;

public class LEET4 {
    public static void main(String[] args) {

        System.out.println(zhongweishu(new int[]{2,5,4},new int[]{3,1,6}));;


    }

    public  static  double  zhongweishu(int A[],int B[]){
        //暴力解法
       /* int totalLength= nums1.length+nums2.length;
        double result;
        int [] nums=new int[totalLength];
        System.arraycopy(nums1,0,nums,0,nums1.length);
        System.arraycopy(nums2,0,nums,nums1.length,nums2.length);
        Arrays.sort(nums);

        if(totalLength%2==0){
            //偶数
            result=((double) nums[nums.length/2-1]+(double) nums[nums.length/2])/2;
        }else {
            //奇数
            result=nums[nums.length/2];
        }
        return  result;*/

       //时间复杂度 O(log(min(m,n)))
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n，为了让m小于等于n
            int[] temp = A; A = B; B = temp; //交换两个数组
            int tmp = m; m = n; n = tmp;//交换两个数组的长度
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;//
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;

}
}
