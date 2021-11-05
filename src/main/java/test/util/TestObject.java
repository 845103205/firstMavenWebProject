package test.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanjian
 * @date 2021/11/5 16:04
 */
public class TestObject {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        List list = new ArrayList();
        list.add(1);
        boolean empty = ObjectUtil.isEmpty(list);
        System.out.println(empty);
    }
}
