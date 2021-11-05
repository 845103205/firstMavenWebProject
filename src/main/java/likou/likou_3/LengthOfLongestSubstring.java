package likou.likou_3;

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        int abc = lengthOfLongestSubstring("abc");
        System.out.println(abc);
    }
    public static int lengthOfLongestSubstring(String s){
        int length = s.length();
        int max=0;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if(isUnique(s,i,j)){
                    max=Math.max(max,j-i);
                }
            }
        }
        return  max;
    }

    public static boolean isUnique(String s,int start,int end){
        Set<Character> characterSet=new HashSet<>();
        for(int i=start;i<end;i++){
            char c = s.charAt(i);
            if(characterSet.contains(c)){
                return  false;
            }else {
                characterSet.add(c);
            }
        }
        return  true;
    }
}
