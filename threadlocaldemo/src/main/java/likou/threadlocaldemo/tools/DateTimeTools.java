package likou.threadlocaldemo.tools;
import cn.hutool.core.util.ObjectUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 常见日期类型处理：字符串、日期Date、长整数new Date(long)，parse(time).getTime()
 *
 * @author hongwei
 */
public class DateTimeTools {

    public static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat DAY = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DAY4 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    public static final SimpleDateFormat DAY0 = new SimpleDateFormat(
            "yyyy年MM月");
    public static final SimpleDateFormat DAY3 = new SimpleDateFormat(
            "yyyyMMdd");
    public static final SimpleDateFormat DAY2 = new SimpleDateFormat(
            "yyyy/MM/dd");
    public static final SimpleDateFormat DAYTIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
    public static final SimpleDateFormat DAYTIME2 = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat DAYTIME3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DAYTIME4 = new SimpleDateFormat(
            "MMddHHmmss");
    public static final SimpleDateFormat DAYTIME5 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.S");
    public static final SimpleDateFormat MICROSECONDS = new SimpleDateFormat(
            "yyyyMMddHHmmssSSS");
    public static final SimpleDateFormat MICROSECONDSID = new SimpleDateFormat(
            "yyMMddHHmmssSSS");
    public static final SimpleDateFormat SMALLMICROSECONDS = new SimpleDateFormat(
            "yyMMddHHmmss");
    public static final SimpleDateFormat MIDDLEMICROSECONDS = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public static final SimpleDateFormat SMALLMIHHMMSS = new SimpleDateFormat(
            "HHmmss");
    public static final SimpleDateFormat SMALLMIHHMMSS2 = new SimpleDateFormat(
            "HH:mm:ss");
    public static final SimpleDateFormat DIAN = new SimpleDateFormat("yyyy.MM.dd");
    public static final SimpleDateFormat DAY5 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    // 月份
    public static final SimpleDateFormat Month = new SimpleDateFormat("yyyy-MM");
    //小时
    public static final  SimpleDateFormat house = new SimpleDateFormat("HH");
    //yyyy-MM-dd HH:mm:ss 星期几
    public static final   SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
    /** 开始时间时分秒 */
    public static final String DATE_BEGIN_TIME_STR = " 00:00:00";

    /** 结束时间时分秒 */
    public static final String DATE_END_TIME_STR = " 23:59:59";

    /**
     *
     *
     * @author 周杰伦 2016-12-9
     *
     */
    public enum FormatType {
        yyyyMMddHHmm,
        /** yyyy, curdate() */
        YEAR,
        /** yyyy-MM-dd, curdate() */
        DAY,
        /** yyyy-MM-dd HH:mm:ss, now() */
        DAYTIME,
        /** yyyyMMddHHmmssSSS, 17位毫秒时间序列号 */
        MICROSECONDS,
        /** yyyyMMddHHmmss, 14位毫秒时间序列号 */
        MIDDLEMICROSECONDS,
        /** yyMMddHHmmss, 12位毫秒时间序列号 */
        SMALLMICROSECONDS,
        /** 1406167122870, System.currentTimeInMillis() */
        JAVA,
        /** 1406166160, unix_timestamp(now( )) */
        MYSQL,
        /** yyyyMMdd */
        DAY3,
        /** yyyy年MM月dd日 */
        DAY4,
        /** HHMMSS */
        SMALLMIHHMMSS,
        /** HH:MM:SS */
        SMALLMIHHMMSS2,
        /** MMddhhmmss */
        DAYTIME4,
        /** yyyy.MM.dd */
        DIAN,
        /** yyyy年MM月dd日 HH：mm */
        DAY5,
        /** yyyy-MM */
        Month,
        /** yyMMddHHmmssSSS */
        MICROSECONDSID
    };

    /**
     * @param time
     *            支持格式：
     *            <li>yyyy-MM-dd, mysql: curdate()</li>
     *            <li>yyyy-MM-dd HH:mm:ss, mysql: now()</li>
     *            <li>1406167122870，java: System.currentTimeInMillis()</li>
     *            <li>1406166160，mysql: unix_timestamp(now())</li>
     */
    public static Date parse(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        try {
            synchronized (DAYTIME) {
                return DAYTIME.parse(time);
            }
        } catch (Exception ex) {
            try {
                synchronized (DAYTIME2) {
                    return DAYTIME2.parse(time);
                }
            } catch (ParseException e) {
                try {
                    synchronized (DAYTIME3) {
                        return DAYTIME3.parse(time);
                    }
                } catch (ParseException e1) {
                }
            }
        }
        try {
            synchronized (DAY) {
                return DAY.parse(time);
            }
        } catch (Exception e) {
            try {
                synchronized (DAY2) {
                    return DAY2.parse(time);
                }
            } catch (ParseException e1) {
            }
        }
        if (time.matches("\\d{10,17}")) {
            final int a = 17;
            final int b = 13;
            final int c = 12;
            final int d = 10;
            final int f = 14;
            final int g = 1000;
            if (time.length() == a) {
                try {
                    synchronized (MICROSECONDS) {
                        return MICROSECONDS.parse(time);
                    }
                } catch (Exception e) {
                }
            } else if (time.length() == b) {
                return new Date(Long.valueOf(time));
            } else if (time.length() == c) {
                try {
                    synchronized (SMALLMICROSECONDS) {
                        return SMALLMICROSECONDS.parse(time);
                    }
                } catch (Exception e) {
                }
            } else if (time.length() == d) {
                return new Date(Long.valueOf(time) * g);
            } else if (time.length() == f) {
                try {
                    synchronized (MIDDLEMICROSECONDS) {
                        return MIDDLEMICROSECONDS.parse(time);
                    }
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * @param type
     *            格式化日期
     *            <li>DAY yyyy-MM-dd, mysql: curdate()</li>
     *            <li>DAYTIME yyyy-MM-dd HH:mm:ss, mysql: now()</li>
     *            <li>JAVA 1406167122870，java: System.currentTimeInMillis()</li>
     *            <li>MYSQL 1406166160，mysql: unix_timestamp(now())</li>
     */
    public static String format(Date date, FormatType type) {
        if (date == null) {
            return null;
        }
        final int a = 1000;
        switch (type) {
            case YEAR:
                synchronized (YEAR) {
                    return YEAR.format(date);
                }
            case DAY:
                synchronized (DAY) {
                    return DAY.format(date);
                }
            case DAYTIME:
                synchronized (DAYTIME) {
                    return DAYTIME.format(date);
                }
            case DAYTIME4:
                synchronized (DAYTIME4) {
                    return DAYTIME4.format(date);
                }
            case MICROSECONDS:
                synchronized (MICROSECONDS) {
                    return MICROSECONDS.format(date);
                }
            case SMALLMICROSECONDS:
                synchronized (SMALLMICROSECONDS) {
                    return SMALLMICROSECONDS.format(date);
                }
            case JAVA:
                return String.valueOf(date.getTime());
            case MYSQL:
                return String.valueOf(date.getTime() / a);
            case DAY3:
                synchronized (DAY3) {
                    return DAY3.format(date);
                }
            case DAY4:
                synchronized (DAY4) {
                    return DAY4.format(date);
                }
            case SMALLMIHHMMSS:
                synchronized (SMALLMIHHMMSS) {
                    return SMALLMIHHMMSS.format(date);
                }
            case SMALLMIHHMMSS2:
                synchronized (SMALLMIHHMMSS2) {
                    return SMALLMIHHMMSS2.format(date);
                }
            case MIDDLEMICROSECONDS:
                synchronized (MIDDLEMICROSECONDS) {
                    return MIDDLEMICROSECONDS.format(date);
                }
            case DIAN:
                synchronized (DIAN) {
                    return DIAN.format(date);
                }
            case DAY5:
                synchronized (DAY5) {
                    return DAY5.format(date);
                }
            case Month:
                synchronized (Month) {
                    return Month.format(date);
                }
            case MICROSECONDSID:
                synchronized (MICROSECONDSID) {
                    return MICROSECONDSID.format(date);
                }
            case yyyyMMddHHmm:
                synchronized (yyyyMMddHHmm) {
                    return yyyyMMddHHmm.format(date);
                }
        }
        return null;
    }

    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 时间字符串转换成date
     *
     * @param timeStr
     * @return
     */
    public static Date isStrToDate(String timeStr) {
        Date date = null;
        try {
            date = DAYTIME.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getNextDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.get(Calendar.DAY_OF_MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date dayAdd(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.get(Calendar.DAY_OF_MONTH) + interval);
        return calendar.getTime();
    }

    /**
     * 获取N月前的起始时间
     *
     * @param months
     *            月份数
     * @return
     */
    public static Date getMonthHeadTime(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - months);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取N月天前的起始时间
     *
     * @param days
     *            天数
     * @return
     */
    public static Date getDayHeadTime(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.get(Calendar.DAY_OF_MONTH) - days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取time时间 N天前的起始时间
     *
     * @param time
     * @param days
     * @return
     */
    public static Date getDayHeadTime(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    /**
     * 获取N小时前的起始时间
     *
     * @param hours
     *            小时数
     * @return
     */
    public static Date getHourHeadTime(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.get(Calendar.HOUR_OF_DAY) - hours);
        return calendar.getTime();
    }

    /**
     * 获取minute分钟前时间
     *
     * @param minute
     * @return
     */
    public static Date getMinuteHeadTime(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        return calendar.getTime();
    }

    /**
     * 将string类型的20151202 转化为2015-12-02
     *
     * @param date
     * @return
     */
    public static String formatString(String date) {
        String returnDate = "";
        if (StringTools.isNotNull(date)) {
            SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                returnDate = sf2.format(sf1.parse(date));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return returnDate;
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        final int a = 7;
        cal.add(Calendar.DAY_OF_WEEK, a);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        final int a = 24;
        cal.set(Calendar.HOUR_OF_DAY, a);
        return cal.getTime();
    }


    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            final int a = 3;
            final int b = 4;
            final int d = 6;
            final int f = 7;
            final int g = 9;
            final int h = 10;
            final int i = 12;

            if (currentMonth >= 1 && currentMonth <= a) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= b && currentMonth <= d) {
                c.set(Calendar.MONTH, a);
            } else if (currentMonth >= f && currentMonth <= g) {
                c.set(Calendar.MONTH, d);
            } else if (currentMonth >= h && currentMonth <= i) {
                c.set(Calendar.MONTH, g);
            }
            c.set(Calendar.DATE, 1);
            // now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
            synchronized (DAYTIME) {
                now = DAYTIME.parse(DAY.format(c.getTime()) + " 00:00:00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            final int a = 3;
            final int b = 31;
            final int d = 4;
            final int f = 6;
            final int g = 5;
            final int q = 30;
            final int w = 7;
            final int e = 9;
            final int r = 8;
            final int m = 10;
            final int v = 12;
            final int p = 11;
            if (currentMonth >= 1 && currentMonth <= a) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, b);
            } else if (currentMonth >= d && currentMonth <= f) {
                c.set(Calendar.MONTH, g);
                c.set(Calendar.DATE, q);
            } else if (currentMonth >= w && currentMonth <= e) {
                c.set(Calendar.MONTH, r);
                c.set(Calendar.DATE, q);
            } else if (currentMonth >= m && currentMonth <= v) {
                c.set(Calendar.MONTH, p);
                c.set(Calendar.DATE, b);
            }
            // now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
            synchronized (DAYTIME) {
                now = DAYTIME.parse(DAY.format(c.getTime()) + " 23:59:59");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得时间差<天>
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getDays(String startTime, String endTime) {
        if (startTime.equals(endTime))
            return 0;
        Date start = parse(startTime);
        Date end = parse(endTime);
        Long timeLongs = end.getTime() - start.getTime();
        Double day = (double) (timeLongs / (1000 * 60 * 60 * 24));
        return day.intValue();
    }

    /**
     * 时间比较
     *
     * @param time1
     * @param time2
     * @return time1小于time2=true,否则false
     */
    public static boolean timeCompare(String time1, String time2) {
        try {
            Date time_1 = DAY.parse(time1);
            Date time_2 = DAY.parse(time2);
            return time_1.before(time_2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 日期时间比较
     *
     * @param time1
     * @param time2
     * @return time1小于time2=true,否则false
     */
    public static boolean dateTimeCompare(String time1, String time2) {
        try {
            Date time_1 = DAYTIME.parse(time1);
            Date time_2 = DAYTIME.parse(time2);
            return time_1.before(time_2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得流水号 yyyyMMddHHmmssSSS
     *
     * @return
     */
    public static String getBizNo() {
        return SnowFlakeGenerator.generator()+"";
    }

    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static Date currentMonthfirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime();

    }

    /**
     * 获取当月的最后一天
     *
     * @return
     */
    public static Date currentMonthLastDay() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();

    }


    /**
     * 获取去年的当月的第一天
     *
     * @return
     */
    public static Date qunianCurrentMonthfirstDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // 设置时间为当前时间
        c.add(Calendar.YEAR, -1);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime();

    }

    /**
     * 获取去年的当月的最后一天
     *
     * @return
     */
    public static Date qunianCurrentMonthLastDay() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date()); // 设置时间为当前时间
        ca.add(Calendar.YEAR, -1);
        ca.add(Calendar.MONTH, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();

    }

    /**
     * 获取传入月的第一天
     *
     * @return
     */
    public static Date currentMonthfirstDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime();

    }

    /**
     * 获取传入月的最后一天
     *
     * @return
     */
    public static Date currentMonthLastDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();

    }

    /**
     * 获取下月的第一天
     *
     * @return
     */
    public static Date nextMonthfirstDay() {
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, 1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return cal_1.getTime();

    }

    /**
     * 获取下月的最后一天
     *
     * @return
     */
    public static Date nextMonthLastDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 2);
        cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
        return  cale.getTime();

    }
    /**
     * 获取前月的第一天
     *
     * @return
     */
    public static String preMonthfirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return format.format(cal_1.getTime());

    }

    /**
     * 获取前月的最后一天
     *
     * @return
     */
    public static String preMonthLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
        return format.format(cale.getTime());

    }

//    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(
//                DateUtil.format(date, DateUtil.FormatType.MIDDLEMICROSECONDS));
//        // System.out.println(format(getDayBegin(date), FormatType.DAYTIME));
//        // System.out.println(format(getNextDayBegin(date),
//        // FormatType.DAYTIME));
//        // System.out.println(format(getNextDayBegin(dayAdd(date, 10)),
//        // FormatType.DAYTIME));
//        // System.out.println(format(getNextDayBegin(dayAdd(date, -23)),
//        // FormatType.DAYTIME));
//        // System.out.println(getHourHeadTime(24));
//        // String time = "2015-09-20 08:05";
//        System.out.println(
//                DateUtil.format(new Date(), FormatType.MIDDLEMICROSECONDS));
//        System.out.println(DateUtil.getCurrentQuarterStartTime());
//        System.out.println(DateUtil.getCurrentQuarterEndTime());
//        System.out.println("-------时间----"
//                + DateUtil.format(getMinuteHeadTime(5), FormatType.DAYTIME));
//        System.out.println("-------两个时间差----" + DateUtil
//                .dateTimeCompare("2017-07-26 10:45:51", "2017-07-26 10:45:50"));
//        System.out.println("-----------转换时间----"
//                + DateUtil.isStrToDate("2017-07-26 10:45:51"));
//        System.out.println("-----------转换时间-222---"
//                + DateUtil.format(DateUtil.isStrToDate("2017-07-26 10:45:51"),
//                        FormatType.DAYTIME));
//    }

    /**
     *
     * @Title: daySpace @Description: (计算天数差) @author Wyh @param begin @param
     *         end @return @throws ParseException @throws
     */
    public static int daySpace(Date begin, Date end) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        String fromDate = simpleFormat.format(begin);
        String toDate = simpleFormat.format(end);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        return (int) ((to - from) / (1000 * 60 * 60 * 24));
    }

    /**
     *
     * @Title: daySpace @Description: (计算小时差) @author Wyh @param begin @param
     *         end @return @throws ParseException @throws
     */
    public static int hoursSpace(Date begin, Date end) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        String fromDate = simpleFormat.format(begin);
        String toDate = simpleFormat.format(end);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        return (int) ((to - from) / (1000 * 60 * 60));
    }
    /**
     *
     * @Title: daySpace @Description: (计算小时差) @author Wyh @param begin @param
     *         end @return @throws ParseException @throws
     *
     *     保留小数 分钟
     */
    public static double hoursSpaceDouble(Date begin, Date end) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        String fromDate = simpleFormat.format(begin);
        String toDate = simpleFormat.format(end);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        double d = (to - from) / (1000 * 60);
        return d;
    }

    /**
     *
     * @Title: daySpace @Description: (计算分钟差) @author Wyh @param begin @param
     *         end @return @throws ParseException @throws
     */
    public static int minutesSpace(Date begin, Date end) throws ParseException {
        if (ObjectUtil.isEmpty(begin) || ObjectUtil.isEmpty(end)){
            return 0;
        }
        SimpleDateFormat simpleFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        String fromDate = simpleFormat.format(begin);
        String toDate = simpleFormat.format(end);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        return (int) ((to - from) / (1000 * 60));
    }
    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static int getWeekOfDateInt(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return w;
    }

    /**
     * 获取本周第一天
     * @return
     */
    public static Date getWeekStartDate(){
        Calendar calendar = Calendar.getInstance();
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK)+1; //获取周开始基准
        int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
        calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
        Date start = calendar.getTime();
        return start;
    }
    /**
     * 获取本周周日
     * @return
     */
    public static Date getWeekEndDate(){
        Calendar calendar = Calendar.getInstance();
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK)+1; //获取周开始基准
        int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
        calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
        calendar.add(Calendar.DAY_OF_WEEK, 6); //开始+6，获取周结束日期
        Date end = calendar.getTime();
        return end;
    }

    //判断是否是同一天
    public static boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }
    //判断是否是同一天
    public static boolean isSameDayHS(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }
    /** 获得上周第一天:yyyy-MM-dd  HH:mm:ss */
    public static Date getUpWeekStartDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,2);
        c.add(Calendar.DATE, -7);
        return c.getTime();
    }

    /** 获得上周最后一天:yyyy-MM-dd  HH:mm:ss */
    public static Date getUpWeekEndDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,2);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    /** 获得上月第一天:yyyy-MM-dd  HH:mm:ss */
    public static Date getUpMonthStartDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-01  00:00:00");
        String  start = startSdf.format(c.getTime());
        Date startDate = null;
        try {
            startDate = DAY.parse(start);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return startDate;
    }

    /** 获得上月最后一天:yyyy-MM-dd  HH:mm:ss */
    public static Date getUpMonthEndDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return c.getTime();
    }

    /** 下周一*/
    public static Date nextWeekStartDay(){
        Calendar calendar = new GregorianCalendar(); //获取当前日期
        calendar.add(Calendar.DAY_OF_WEEK,7-calendar.get(Calendar.DAY_OF_WEEK)+2);
        return calendar.getTime();
    }
    /** 下周日*/
    public static Date nextWeekEndDay(){
        Calendar calendar = new GregorianCalendar(); //获取当前日期
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)-1);
        calendar.add(Calendar.DAY_OF_WEEK,7-calendar.get(Calendar.DAY_OF_WEEK)+2);
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        return calendar.getTime();
    }

    /**
     * Unix时间戳转换成字符串时间
     * @param timestampString
     * @return
     */
    public static String timeStamp2Date(String timestampString){
        return DateTimeTools.format(new Date(Long.parseLong(timestampString) * 1000), FormatType.DAYTIME);
    }

    /**
     * 方法说明: 传入日期清零时分秒毫秒.
     * 如: 传入2020-03-01 18:26:15, 则获取 2020-03-01 00:00:00
     * @author Brando 2020/3/7 11:25
     * @param curDate: 当前需要清零的日期, null 代表当前日期.
     */
    public static Date getZeroDate(Date curDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        if(curDate != null) {
            cal.setTime(curDate);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date d = cal.getTime();
        return d;
    }

    /**
     * 方法说明: 传入日期最大化当天日期.
     * 如: 传入2020-03-01 18:26:15, 则获取 2020-03-01 23:59:59
     * @author Brando 2020/3/7 11:25
     * @param curDate: 当前需要清零的日期, null 代表当前日期.
     */
    public static Date getLastDate(Date curDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        if(curDate != null) {
            cal.setTime(curDate);
        }
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date d = cal.getTime();
        return d;
    }


    /**
     * 获取某年第一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year
     *            年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    public static void main(String[] args) throws Exception {

        //某时某分某秒执行
//        System.out.println(DateTimeTools.getCron("18:30:00","HH:mm:ss","ss mm HH ?"));时分秒
        //日 时 分 秒
        // System.out.println(DateTimeTools.getCron("03 18:30:00","dd HH:mm:ss","ss mm HH dd * ?"));
        //月 日 时 分 秒
//        System.out.println(DateTimeTools.getCron("03-01 18:30:00","MM-dd HH:mm:ss","ss mm HH dd MM ?"));
        //年 月 日 时 分 秒
//        System.out.println(DateTimeTools.getCron("2020-03-01 18:30:00","yyyy-MM-dd HH:mm:ss","ss mm HH dd MM ? yyyy"));
//        int s = "00 30 18 ? 07 星期一 2020".indexOf("星期");
//        int len = "00 30 18 ? 07 星期一 2020".length();
//        System.out.println("00 30 18 ? 07 星期一 2020".replace("星期一","2"));
//        System.out.println("00 30 18 ? 07 星期七 2020".substring(s,s+4));
//        SimpleDateFormat myFmt3=new SimpleDateFormat("HH:mm:ss E,E");
//
//        System.out.println(myFmt3.parse("18:30:00 星期一,星期二"));
//        System.out.println(myFmt3.format(myFmt3.parse("18:30:00 星期一,星期二")));
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());//填入当前时间
//        int year = c.get(Calendar.YEAR);    //获取年
//        int month = c.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
//        int day = c.get(Calendar.DAY_OF_MONTH);    //获取当前天数
//        int first = c.getActualMinimum(c.DAY_OF_MONTH);    //获取本月最小天数
//        int last = c.getActualMaximum(c.DAY_OF_MONTH);    //获取本月最大天数
//        int time = c.get(Calendar.HOUR_OF_DAY);       //获取当前小时
//        int min = c.get(Calendar.MINUTE);          //获取当前分钟
//        int xx = c.get(Calendar.SECOND);          //获取当前秒
////        System.out.println("当前时间："+year + "-" + month + "-"+ day + " "+time + ":" + min +":" + xx);
//        String s = DateTimeTools.SMALLMIHHMMSS2.format(DateTimeTools.SMALLMIHHMMSS2.parse("00:00:00"));
//        System.out.println(DateTimeTools.DAYTIME3.parse("2020-06-20"+" "+s));
//        System.out.println(getFirstDayOfMonth("2018-09-28"));
//        System.out.println(DAY0.format(DateTimeTools.DAY.parse("2020-12-01")));
//        double d = 14.14588888;
//        double sumD = 0.1;
//        BigDecimal b1 = new BigDecimal(Double.toString(sumD));
//        BigDecimal b2 = new BigDecimal(Double.toString(d));
//        sumD = b1.add(b2).doubleValue();
//
        System.out.println(DAY4.format(DateTimeTools.DAY.parse(DateTimeTools.getFirstDayOfMonth("2020-08-01"))));
        System.out.println(DateTimeTools.getTimesMonthmorning());
        System.out.println(DateTimeTools.getTimesMonthnight());
        System.out.println(DateTimeTools.DAYTIME.format(DateTimeTools.getLastDate(DateTimeTools.currentMonthLastDay())));
    }

    public static String getFirstDayOfMonth(String date_str) throws Exception {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cale.setTime(formatter.parse(date_str));
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = formatter.format(cale.getTime()); // 当月第一天 2019-02-01
        return firstDayOfMonth;
    }

    public static String getLastDayOfMonth(String date_str) throws Exception {
        Calendar cale = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cale.setTime(formatter.parse(date_str));
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastDayOfMonth = formatter.format(cale.getTime()); // 当月最后一天 2019-02-28
        return lastDayOfMonth;
    }

    /**
     * 方法说明: 判断date是否在strDateBegin 和 strDateEnd 之间
     * @author luomi 2021/4/16 10:08
     */
    public static boolean isInDate(Date date, Date dateBegin,Date dateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDateBegin = sdf.format(dateBegin);
        String strDateEnd = sdf.format(dateEnd);
        String strDate = sdf.format(date);
        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
            // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM&& strDateM <= strDateEndM) {
                return true;
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
                return true;
            }else if (strDateH >= strDateBeginH && strDateH == strDateEndH&& strDateM <= strDateEndM) {
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
                return true;
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH&& strDateM == strDateEndM && strDateS <= strDateEndS) {
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
