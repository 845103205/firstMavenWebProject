package test.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class Array {
    static  Integer[] array = new Integer[3];

    public Integer[] getArray() {
        return array;
    }

    public static void main(String[] args) {
        array[0]=2;
        array[1]=3;
        array[2]=4;
        Integer[] abc = new Integer[]{1,1,1};
        int i = Arrays.binarySearch(array, 666);
        System.out.println(i);
        ArrayList list =new ArrayList();

    }
}
