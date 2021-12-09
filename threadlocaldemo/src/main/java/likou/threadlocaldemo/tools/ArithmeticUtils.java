package likou.threadlocaldemo.tools;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
  * @Description: 高精确处理常用的数学运算
  * @author wx 2021/4/17
  */
public class ArithmeticUtils {

    /**
     * 提供精确的加法运算
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 =  BigDecimal.valueOf(v2);
        return b1.add(b2).stripTrailingZeros().doubleValue();
    }

    /**
     * 提供精确的加法运算
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2) {
        if (ObjectUtil.isEmpty(v1) && ObjectUtil.isEmpty(v2)){
            return "0";
        }
        if (ObjectUtil.isEmpty(v1) && ObjectUtil.isNotEmpty(v2)){
            return v2;
        }
        if (ObjectUtil.isNotEmpty(v1) && ObjectUtil.isEmpty(v2)){
            return v1;
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).stripTrailingZeros().toPlainString();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留scale 位小数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2, int scale) {
        if (scale < BigDecimal.ROUND_UP) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (StrUtil.isEmpty(v1)){
            v1 = String.valueOf(BigDecimal.ROUND_UP);
        }
        if (StrUtil.isEmpty(v2)){
            v2 = String.valueOf(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static String addString(String v1, String v2) {
        DecimalFormat df = new DecimalFormat("##.##");
        if (StrUtil.isEmpty(v1)){
            v1 = String.valueOf(BigDecimal.ROUND_UP);
        }
        if (StrUtil.isEmpty(v2)){
            v2 = String.valueOf(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return df.format(b1.add(b2).setScale(BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }

    /**
     * 提供精确的减法运算
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        return sub(v1,v2,BigDecimal.ROUND_HALF_UP);
    }

    /**
      * @Description: 减法运算
      * @param v1 被减数
      * @param v2 减数
      * @param scale 保留小数位数
      * @return double
      * @author wx 2021/6/17
      */
    public static double sub(double v1, double v2, int scale) {
        BigDecimal b1 =  BigDecimal.valueOf(v1);
        BigDecimal b2 =  BigDecimal.valueOf(v2);
        return b1.subtract(b2).setScale(BigDecimal.ROUND_CEILING, scale).stripTrailingZeros().doubleValue();
    }


    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(String v1, String v2) {
        if (StrUtil.isEmpty(v1)){
            v1 = String.valueOf(BigDecimal.ROUND_UP);
        }
        if (StrUtil.isEmpty(v2)){
            v2 = String.valueOf(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).stripTrailingZeros();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留scale 位小数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2, int scale) {
        if (scale < BigDecimal.ROUND_UP) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (StrUtil.isEmpty(v1)){
            v1 = String.valueOf(BigDecimal.ROUND_UP);
        }
        if (StrUtil.isEmpty(v2)){
            v2 = String.valueOf(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差并返回绝对值
     */
    public static String subOrAbs(String v1, String v2) {
        DecimalFormat df = new DecimalFormat("##.##");
        if (StrUtil.isEmpty(v1)){
            v1 = String.valueOf(BigDecimal.ROUND_UP);
        }
        if (StrUtil.isEmpty(v2)){
            v2 = String.valueOf(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return df.format(Math.abs(b1.subtract(b2).setScale(BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().doubleValue()));
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 =  BigDecimal.valueOf(v1);
        BigDecimal b2 =  BigDecimal.valueOf(v2);
        return b1.multiply(b2).setScale(BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2, int scale) {
        BigDecimal b1 =  BigDecimal.valueOf(v1);
        BigDecimal b2 =  BigDecimal.valueOf(v2);
        return round(b1.multiply(b2).doubleValue(), scale);
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static String mul(String v1, String v2, int scale) {
        if (scale < BigDecimal.ROUND_UP) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (StrUtil.isEmpty(v1)){
            v1 = String.valueOf(BigDecimal.ROUND_UP);
        }
        if (StrUtil.isEmpty(v2)){
            v2 = String.valueOf(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {
        return div(v1, v2, BigDecimal.ROUND_CEILING);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < BigDecimal.ROUND_UP) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(v1)) == BigDecimal.ROUND_UP || BigDecimal.ZERO.compareTo(BigDecimal.valueOf(v2)) == BigDecimal.ROUND_UP){
            return BigDecimal.ROUND_UP;
        }
        BigDecimal b1 =  BigDecimal.valueOf(v1);
        BigDecimal b2 =  BigDecimal.valueOf(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < BigDecimal.ROUND_UP) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (StrUtil.isEmpty(v1) || StrUtil.isEmpty(v2)){
            return String.valueOf(BigDecimal.ROUND_UP);
        }
        if (ObjectUtil.equal(v1,"0")){
            return "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String div(String v1, String v2) {
        DecimalFormat df = new DecimalFormat("##.##");
        if (StrUtil.isEmpty(v1) || StrUtil.isEmpty(v2)){
            return String.valueOf(BigDecimal.ROUND_UP);
        }
        if (ObjectUtil.equal(v1,"0")){
            return "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return df.format(b1.divide(b2, BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }

    /**
     * 计算百分比
     * @param v1
     * @param v2
     * @return
     */
    public static String percentage(String v1, String v2) {
        DecimalFormat df = new DecimalFormat("##.#%");
        df.setMaximumFractionDigits(BigDecimal.ROUND_CEILING);
        if (StrUtil.isEmpty(v1) || StrUtil.isEmpty(v2)){
            return df.format(0);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return df.format(b1.divide(b2, BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }


    /**
     * 没有%号
     * @param v1
     * @param v2
     * @return
     */
    public static String percentageNone(String v1, String v2) {
        DecimalFormat df = new DecimalFormat("##.#");
        df.setMaximumFractionDigits(2);
        if (StrUtil.isEmpty(v1) || StrUtil.isEmpty(v2)){
            return df.format(0);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return df.format(b1.divide(b2, BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros());
    }


    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b =  BigDecimal.valueOf(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().doubleValue();
    }

    /**
      * @Description:
      * @param v      需要四舍五入的数字
      * @param scale 小数点后保留几位
      * @return java.lang.String
      * @author wx 2021/4/23
      */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * @Description:
     * @param v      需要四舍五入的数字
     * @return java.lang.String
     * @author wx 2021/4/23
     */
    public static String round(String v) {
        if (StrUtil.isEmpty(v)){
            return "0";
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 取余数
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static String remainder(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * 取余数  BigDecimal
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static BigDecimal remainder(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        return v1.remainder(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 比较大小
     *
     * @param v1 被比较数
     * @param v2 比较数
     * @return 如果v1 大于v2 则 返回true 否则false
     */
    public static boolean compare(String v1, String v2) {
        if (StrUtil.isEmpty(v1)){
            v1 = "0";
        }
        if (StrUtil.isEmpty(v2)){
            v2 = "0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        res = bj > 0;
        return res;
    }

    /**
     * @Description: 计算百分比
     * @param v1 被除数
     * @param v2 除数
     * @param isPercentage 是否计算成百分比 true是 false否
     * @return java.lang.String
     * @author wx 2021/8/5
     */
    public static String queryMul(String v1,String v2,boolean isPercentage){
        if (isPercentage){
            // 系统综合效率、实时效率、负载率不能转换为百分比，转换成百分比数值就太大了
            return ArithmeticUtils.mul(ArithmeticUtils.div(v1,v2,BigDecimal.ROUND_UNNECESSARY),"100",BigDecimal.ROUND_CEILING);
        }
        return ArithmeticUtils.div(v1,v2,BigDecimal.ROUND_CEILING);
    }

    /**
     * 同比/环比计算
     * @param bcount 本期数值
     * @param scount 同比:上年同期数值  环比:上期数值
     * @return
     */
    public static String monthOnMonth(String bcount,String scount){
        DecimalFormat df = new DecimalFormat("##.##");
        df.setMaximumFractionDigits(2);
        if (StrUtil.isEmpty(bcount) || StrUtil.isEmpty(scount)){
            return df.format(BigDecimal.ROUND_UP);
        }
        if (StrUtil.equals(scount,Integer.toString(BigDecimal.ROUND_UP))){
            return df.format(BigDecimal.ROUND_UP);
        }
        BigDecimal b1 = new BigDecimal(bcount);
        BigDecimal b2 = new BigDecimal(scount);
        return b1.subtract(b2).divide(b2, BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros().toPlainString();
    }

    /**
     * 同比/环比计算
     * @param bcount 本期数值
     * @param scount 同比:上年同期数值  环比:上期数值
     * @return
     */
    public static String monthOnMonth(BigDecimal bcount,BigDecimal scount,String pattern){
        DecimalFormat df = new DecimalFormat(pattern);
        df.setMaximumFractionDigits(2);
        if (ObjectUtil.isEmpty(bcount) || ObjectUtil.isEmpty(scount)){
            return df.format(0);
        }
        if (scount.doubleValue() == 0){
            return df.format(0);
        }
        return df.format(bcount.subtract(scount).divide(scount, BigDecimal.ROUND_CEILING, BigDecimal.ROUND_HALF_DOWN).stripTrailingZeros());
    }

    public static String monthOnMonth(BigDecimal bcount,BigDecimal scount){
        return monthOnMonth(bcount, scount,"##.##%");
    }

    /**
     * 方法说明: 拟合度计算
     * @author BrandoLv 2021-06-09 14:41
     * @param yTrueList 实际值
     * @param yPredList 预测值
     * @return 拟合度, 小于0时, 返回0.
     */
    public static Double rSquared(List<Double> yTrueList, List<Double> yPredList) {
        List<Double> resYData = new ArrayList<>();
        Double ssRes = 0D;
        Double yDataMean = 0D;
        Double yDataSum = 0D;

        for(int i = 0; i < yTrueList.size(); i++) {
            Double v1 = yTrueList.get(i);
            Double v2 = yPredList.get(i);
            Double v3 = v1 - v2;
            resYData.add(v3);
            ssRes += Math.pow(v3, 2);
            yDataSum += v1;
        }
        yDataMean = yDataSum / yTrueList.size();

        Double ssTot = 0D;
        for(int i = 0; i < yTrueList.size(); i++) {
            Double v1 = yTrueList.get(i);
            Double v2 = v1 - yDataMean;
            ssTot += Math.pow(v2, 2);
        }
        Double squared = 1 - ssRes / ssTot;
        if(squared < 0) {
            return 0D;
        }
        return squared;
    }

//    public static void main(String[] args) {
//        List<Double> y_true = new ArrayList<>();
//        y_true.add(98.0);
//        y_true.add(95.0);
//        y_true.add(90.0);
//        y_true.add(80.0);
//        List<Double> y_pred = new ArrayList<>();
//        y_pred.add(95.0);
//        y_pred.add(94.0);
//        y_pred.add(80.0);
//        y_pred.add(40.0);
//        System.out.println(rSquared(y_true, y_pred));
//    }



}
