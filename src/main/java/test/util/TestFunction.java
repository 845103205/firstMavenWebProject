package test.util;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * @author tanjian
 * @date 2021/11/8 9:11
 */
public class TestFunction {
    public static void main(String[] args) {
        Data data = new Data();
        data.setData(3.1);
        data.setName("重庆");
        Data data1 = new Data();
        data1.setData(3.2);
        data1.setName("重庆");

        Data data2 = new Data();
        data2.setData(3.4);
        data2.setName("上海");

        List<Data> list = new ArrayList<>();
        list.add(data);
        list.add(data1);
        list.add(data2);

        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(Data::getName, Collectors.summingDouble(value ->
                ObjectUtil.isNotEmpty(value.getData()) ? value.getData() : 0)));
        System.out.println(collect);
    }
}
@lombok.Data
class Data{
    private double data;
    private String name;

}
