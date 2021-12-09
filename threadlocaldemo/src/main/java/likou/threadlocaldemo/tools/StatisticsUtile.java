package likou.threadlocaldemo.tools;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wx
 * @Date: 2020/8/5
 */
public final class StatisticsUtile {
    /** 温度【常量】 */
    public static final String[] TEMPERATURE_HUMIDITY ={"PUB_CHWR_T","PUB_CHWS_T","PUB_CWS_T","PUB_CWR_T","PUB_CW_DIFFERENCE","PUB_CHW_DIFFERENCE"};

    /** 室外温湿度【常量】 */
    public static final String[] temperatureAndHumidity = {"PUB_OA_H","PUB_OA_T"};
    public static final String PUB_OA_H = "PUB_OA_H";// 室外湿度
    public static final String PUB_OA_T = "PUB_OA_T";// 室外温度

    /** 压力【常量】 */
    public static final String[] PRESSURE = {"PUB_CHWS_P","PUB_CHWR_P","PUB_CWS_P","PUB_CWR_P","PUB_CW_PRESSURE","PUB_CHW_PRESSURE"};

    /** 流量【常量】 */
    public static final String[] FLOW = {"PUB_CHW_FL","PUB_CW_FL"};

    /**电量**/
    public static  final String UNI_USE_Q = "UNI_USE_Q";
    /**瞬时流量**/
    public static  final String UNI_INS_FCONUT = "UNI_INS_FCONUT";

    /** 冷热量【常量】 */
    public static final String[] COOLING_CAPACITY =  {"PUB_COP_InsC","PUB_COP_InsH"};

    /**瞬时冷量**/
    public static  final String PUB_COP_INSC = "PUB_COP_InsC";
    /**瞬时热量**/
    public static  final String PUB_COP_INSH = "PUB_COP_InsH";

    /** 冷量总累计 */
    public static final String PR_PUB_BTU_ACCC =  "PUB_BTU_AccC";
    /** 热量总累计 */
    public static final String PUB_BTU_AccH =  "PUB_BTU_AccH";
    /** 热平衡*/
    public static final String[] PUB_ACCH_POISE =  {"PUB_ACCH_Poise"};
    /** 机房瞬时COP【常量】 */
    public static final String SYSTEM_EFFICIENCY_COP = "PUB_COP_EER";

    /** 循环水泵总功率 */
    public static final String PUB_COP_CHWP_P = "PUB_COP_CHWP_P";
    /** 冷却水泵总功率 */
    public static final String PUB_COP_CWP_P = "PUB_COP_CWP_P";
    /** 冷却塔总功率 */
    public static final String PUB_COP_CT_P = "PUB_COP_CT_P";
    /** 主机总功率 */
    public static final String PUB_COP_CH_P = "PUB_COP_CH_P";
    /** 机房总功率 */
    public static final String PUB_PLANT_P = "PUB_PLANT_P";



    /////////电力参数/////////////////
    /**功率因数*/
    public static  final String[] COS={"UNI_COS"};
    /**有功功率*/
    public static  final String P="UNI_P";
    /**A相电流*/
    public static  final String[] IA={"UNI_IA"};
    /**B相电流*/
    public static  final String[] IB={"UNI_IB"};
    /**C相电流*/
    public static  final String[] IC={"UNI_IC"};
    /**A相电压*/
    public static  final String[] UA={"UNI_UA"};
    /**B相电压*/
    public static  final String[] UB={"UNI_UB"};
    /**C相电压*/
    public static  final String[] UC={"UNI_UC"};
    /**设备运行**/
    public static  final String EQ_PUB_RUN="UNI_RUN";

    ////////水参数////////////////////////
    /**水流量**/
    public static  final String UNI_W_FL="UNI_W_FL";
    /**累计用水量**/
    public static  final String UNI_USE_W="UNI_USE_W";

    /**非居民用水价格*/
    public static final BigDecimal WATER_UNIT_PRICE = BigDecimal.valueOf(4.55);

    ////////气参数////////////////////////
    /**累计用气量**/
    public static  final String UNI_USE_G = "UNI_USE_G";

    /**非居民用气价格*/
    public static final BigDecimal GAS_UNIT_PRICE = BigDecimal.valueOf(2.074);

    ////////传感设备参数////////////////////////
    /**环境温度*/
    public static  final String UNI_TH_T="UNI_TH_T";
    /**环境湿度*/
    public static  final String UNI_TH_H="UNI_TH_H";

    ////////碳转换比例////////////////////////
    // 1.电转碳：1kW.h=0.638kg（二氧化碳）2.水转碳：1m³=0.194kg（二氧化碳）3.气转碳：1m³=2.1kg（二氧化碳）
    public static  final BigDecimal CONVERT_CARBON_Q = new BigDecimal("0.638");
    public static  final BigDecimal CONVERT_CARBON_W = new BigDecimal("0.194");
    public static  final BigDecimal CONVERT_CARBON_G = new BigDecimal("2.1");
    ////////煤转换比例////////////////////////
    // 1.电转碳：1kW.h=0.1229Kg(标煤)2.水转碳：1m³=0.086Kg(标煤)3.气转碳：1m³=1.33kg（标煤）
    public static  final BigDecimal CONVERT_COAL_Q = new BigDecimal("0.1229");
    public static  final BigDecimal CONVERT_COAL_W = new BigDecimal("0.086");
    public static  final BigDecimal CONVERT_COAL_G = new BigDecimal("1.33");

    /**碳转换为树（一棵树每天吸收5kg）*/
    public static  final BigDecimal CONVERT_TREE = new BigDecimal("0.2");

    /**
     * 计算传递查询时间与日、周、月、年开始时间间隔小时数
     * @param dataType
     * @param time
     * @return
     */
    public static String calculationHour(Integer dataType, String time){
        String hour = null;
        DateTime dateTime;
        if (dataType == 0){
            dateTime = DateUtil.parse(time);
            hour = String.valueOf(DateUtil.between(DateUtil.endOfDay(dateTime),DateUtil.beginOfDay(dateTime), DateUnit.HOUR ));
        }else if (dataType ==1){
            dateTime = DateUtil.parse(time);
            hour = String.valueOf(DateUtil.between(dateTime,DateUtil.beginOfWeek(dateTime), DateUnit.HOUR ));
        }else if (dataType ==2){
            dateTime = DateUtil.parse(time,"yyyy-MM");
            hour = String.valueOf(DateUtil.between(DateUtil.endOfMonth(dateTime),DateUtil.beginOfMonth(dateTime), DateUnit.HOUR ));
        }else if (dataType ==3){
            dateTime = DateUtil.parse(time,"yyyy");
            hour = String.valueOf(DateUtil.between(DateUtil.endOfYear(dateTime),DateUtil.beginOfYear(dateTime), DateUnit.HOUR ));
        }
        return hour;
    }


    /**
     * 通过indexof匹配想要查询的字符
     * @return
     */
    public static String checkKey(String key, String[] filters) {
        for (int i = 0; i < filters.length; i++) {
            if (filters[i].indexOf(key)>-1){
                return filters[i];
            }
        }
        return null;
    }
    /**
     * 判断两个集合是否存在交集
     * @param str 字符串集合
     * @param labels list集合
     * @return
     */
    public static Boolean intersection(String str, List<String> labels){
        boolean flg = false;
        if ( ObjectUtil.isNotEmpty(str) && ObjectUtil.isNotEmpty(labels)) {
            for (int i = 0; i < labels.size(); i++) {
                if (str.contains(labels.get(i))){
                    flg = true;
                }
            }
        }
        return flg;
    }

    public static Boolean intersection(String str, List<String> labels,Integer categoryTypeId,Integer equipmentInfoCategory){
        if (ObjectUtil.isNotEmpty(categoryTypeId) && ObjectUtil.isNotEmpty(equipmentInfoCategory)){
             if (categoryTypeId == equipmentInfoCategory){
                 return intersection(str,labels);
             }
        }
        return intersection(str,labels);
    }
    /**
     * 组装前段显示字段
     * @param label 标签
     * @return
     */
    public static  String columnLabel(String label){
        if (ObjectUtil.isNotEmpty(label)){
            return "column"+label.subSequence(getCharacterPosition(label,"_",1),label.length());
        }
        return null;
    }

    /**
      * @Description: 根据查询标签组装前段显示字段
      * @param tags 数据端采集标签
      * @param labels 前段显示标签集合
      * @return java.lang.String
      * @author wx 2021/4/25
      */
    public static  String columnLabel(String tags,List<String> labels){
        if (ObjectUtil.isNotEmpty(tags)){
            String[] tag = tags.split(",");
            for (int i = 0; i < tag.length; i++) {
                if (labels.contains(tag[i])){
                    return columnLabel(tag[i]);
                }
            }
        }
        return null;
    }

    /**
     * 查找字符在字符串中第几次出现的位置
     * @param string
     * @param character
     * @param num
     * @return
     */
    public static int getCharacterPosition(String string,String character,int num){
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(character).matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
            mIdx++;
            //当"/"符号第三次出现的位置
            if(mIdx == num){
                break;
            }
        }
        return slashMatcher.start();
    }


}
