package likou.threadlocaldemo.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 常见日期类型处理：字符串、日期Date、长整数new Date(long)，parse(time).getTime()
 *
 * @author hongwei
 */
public class CronTools {

    //每天某个时间
    public static final String HHmmssCronFormat = "ss mm HH * * ?";
    //每月某天某个时间
    public static final String ddHHmmssCronFormat = "ss mm HH dd * ?";
    //每年某月1号的某一天
    public static final String MMddHHmmssCronFormat = "ss mm HH dd MM ?";
    //年 月 日 时 分 秒
    public static final String yyyyMMddHHmmssCronFormat = "ss mm HH dd MM ? yyyy";
    //每周
    public static final String weeklyCronFormat = "ss mm HH ? * E";


    public enum CronFormatType {

        HHmmssCronFormat,
        ddHHmmssCronFormat,
        MMddHHmmssCronFormat,
        yyyyMMddHHmmssCronFormat,
        weeklyCronFormat,
    };

    /**
     * 方法说明: parm 包括如下key。秒，分，时，日，月，周，年
     * second,minute,hour,day,mouth,week,year
     * @author luomi 2020/6/4 16:40
     */
    public static String getCron(String cronFormat, Map<String,String> parm) {
        String formatTimeStr = cronFormat;
        String second = parm.get("second");
        String minute = parm.get("minute");
        String hour = parm.get("hour");
        String day = parm.get("day");
        String month = parm.get("month");
        String week = parm.get("week");
        String year = parm.get("year");
        if(cronFormat.equals(CronTools.weeklyCronFormat)){
            //ss mm HH ? * E
            //取 秒，分，时，周来写corn
            formatTimeStr = formatTimeStr.replace("ss",second);
            formatTimeStr = formatTimeStr.replace("mm",minute);
            formatTimeStr = formatTimeStr.replace("HH",hour);
            formatTimeStr = formatTimeStr.replace("E",week);
        }else if(cronFormat.equals(CronTools.HHmmssCronFormat)){
            formatTimeStr = formatTimeStr.replace("ss",second);
            formatTimeStr = formatTimeStr.replace("mm",minute);
            formatTimeStr = formatTimeStr.replace("HH",hour);
        }else if(cronFormat.equals(CronTools.ddHHmmssCronFormat)){
            formatTimeStr = formatTimeStr.replace("ss",second);
            formatTimeStr = formatTimeStr.replace("mm",minute);
            formatTimeStr = formatTimeStr.replace("HH",hour);
            formatTimeStr = formatTimeStr.replace("dd",day);
        }else if(cronFormat.equals(CronTools.MMddHHmmssCronFormat)){
            formatTimeStr = formatTimeStr.replace("ss",second);
            formatTimeStr = formatTimeStr.replace("mm",minute);
            formatTimeStr = formatTimeStr.replace("HH",hour);
            formatTimeStr = formatTimeStr.replace("dd",day);
            formatTimeStr = formatTimeStr.replace("MM",month);
        }else if(cronFormat.equals(CronTools.yyyyMMddHHmmssCronFormat)){
            formatTimeStr = formatTimeStr.replace("ss",second);
            formatTimeStr = formatTimeStr.replace("mm",minute);
            formatTimeStr = formatTimeStr.replace("HH",hour);
            formatTimeStr = formatTimeStr.replace("dd",day);
            formatTimeStr = formatTimeStr.replace("MM",month);
            formatTimeStr = formatTimeStr.replace("yyyy",year);
        }
        return formatTimeStr;
    }

    public static String getCron(String cronFormat,Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(cronFormat);
        String formatTimeStr = null;
        if (Objects.nonNull(date)) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static void main(String[] args) {
//        Map<String,String> parm = new HashMap<>();
//        parm.put("year","2020");
//        parm.put("mouth","3,5,6");
//        parm.put("day","1,5,9");
//        parm.put("week","2,5,6");
//        parm.put("hour","06");
//        parm.put("minute","30");
//        parm.put("second","00");
//        System.out.println(getCron(CronTools.weeklyCronFormat,parm));
//        System.out.println(getCron(CronTools.HHmmssCronFormat,parm));
//        System.out.println(getCron(CronTools.ddHHmmssCronFormat,parm));
//        System.out.println(getCron(CronTools.MMddHHmmssCronFormat,parm));
//        System.out.println(getCron(CronTools.yyyyMMddHHmmssCronFormat,parm));
    }
}

