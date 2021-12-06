package test.jichu;

/**
 * @author tanjian
 * @date 2021/12/3 17:17
 */
public class Test {
    public static void main(String[] args) {
        int a = 15;
        //八进制
        int b = 015;
        //十六进制
        int c = 0x15;
        //二进制
        int d = 0b1101;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

        int age = (byte) 300;
        System.out.println(age);
    }
}
