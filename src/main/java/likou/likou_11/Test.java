package likou.likou_11;

public class Test {
    public static void main(String[] args) {
        int i = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(i);
    }

    public static int maxArea(int[] height) {
       int lowIndex =0;
       int highIndex = height.length-1;
       int result = 0;
       while (lowIndex < highIndex){
           int area = Math.min(height[lowIndex],height[highIndex])*(highIndex-lowIndex);
           result = Math.max(result,area);
           if(height[lowIndex]<=height[highIndex]){
               lowIndex++;
           }else {
               highIndex--;
           }
       }
       return  result;
    }
}
