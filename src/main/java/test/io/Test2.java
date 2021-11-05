package test.io;

import java.io.File;

public class Test2 {

    public static void main(String[] args) {
       printFileTree(new File("D:\\学习视频\\day02_MySQL基础\\视频").getParentFile());
    }

    public static void printFileTree(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1.getName());
                printFileTree(file1);
            }
        }
    }
}
