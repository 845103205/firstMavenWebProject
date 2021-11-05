package test.aop;

public class Calculator {
    public int chu(int i,int j){
        int k=i/j;
        System.out.println("执行了方法");
        return k;
    }
}
