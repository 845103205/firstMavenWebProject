package likou.likou_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring_1 {

    public static void main(String[] args) {
       // int abc = lengthOfLongestSubstring("pewkertw");
        int abc = lengthOfLongestSubstring("dvdf");
        System.out.println(abc);
    }
    public static int lengthOfLongestSubstring(String s){
      /*  int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            //判断map中是否包含有该字符串
            if (map.containsKey(s.charAt(j))) {
                //如果有该字符串则更新i的值，i表示重复字符串的位置，i=0表示没有遇到重复的字符串
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //记录最大的无重复的子串长度
            ans = Math.max(ans, j - i + 1);
            //记录每个字符串的位置，重复字符串则替换之前的字符串。
            map.put(s.charAt(j), j + 1);
        }
        return ans;*/

      /*  int length = s.length();
        Map<Character,Integer> map=new HashMap<>();
        int ans=0;
        for (int i = 0,j=0; i < length; i++) {
            if(map.containsKey(s.charAt(i))){
                //如果遇到重复字符串则，则记录这次重复字符串的位置，与上次比较
                j=Math.max(map.get(s.charAt(i)),j);
            }
            //记录最长的无重复字符串长度
            ans=Math.max(ans,i-j);
            //记录每一个字符和字符所在的位置索引
            map.put(s.charAt(i),i);
        }
        return  ans;*/


        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;

    }
}
