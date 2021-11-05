package test.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author tanjian
 * @date 2021/11/4 18:42
 */
public class TestDate {
    public static void main(String[] args) {
     test1();
    }

    private static void  test1(){
        DateTime dateTime = DateUtil.offsetDay(new Date(), -70);
        //System.out.println(dateTime);
        DateTime dateTime1 = DateUtil.offsetHour(new Date(), -1);
        System.out.println(dateTime1);
    }

    public static void test(){
        long l = System.currentTimeMillis();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean after = new Date().after(new Date(l+1000));
        System.out.println(after);
    }

    public static void test2(){
        String format = DateUtil.format(new Date(System.currentTimeMillis()), "yyy-MM-d HH:mm:ss");
        System.out.println(format);
    }
}
