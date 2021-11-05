package likou.threadlocaldemo.common;

import org.apache.commons.lang3.time.DateFormatUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by root on 17-1-5.
 */

public final class Helpers {
    //日期的处理
    //mode 前向的单位 Calendar.DATE Calendar.MONTH Calendar.YEAR
    public static long datePre(int nums, int mode) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(mode);
        calendar.set(mode, month - nums);
        return calendar.getTime().getTime();
    }

    //去掉估计标准的T字符； 使用中国的时区
    public static String dateNowFormat() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("GMT+8"));
    }

    //
    public static String dateFormat(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("GMT+8"));
    }
    public static String dateFormat(Long timestamp) {
        return DateFormatUtils.format(new Date(timestamp), "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("GMT+8"));
    }

    public static String dateFormatHour(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return String.format("%d点", calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static String dateFormatDay(Long timestamp) {
        return DateFormatUtils.format(new Date(timestamp), "MM-dd", TimeZone.getTimeZone("GMT+8"));
    }

    public static String dateFormatWeek(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
            default:
                return "周一";
        }
    }

    public static String dateFormatDayDot(Long timestamp) {
        return DateFormatUtils.format(new Date(timestamp), "yyyy.MM.dd", TimeZone.getTimeZone("GMT+8"));
    }

    public static String dateFormat(Date date, String patten) {
        return DateFormatUtils.format(date, patten, TimeZone.getTimeZone("GMT+8"));
    }

    public static long dateToLong(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * IP Long 转 字符串
     *
     * @return String
     */
    public static final String ipToString(Long ipL) {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.valueOf(ipL >>> 24)).append(".");
        sb.append(String.valueOf((ipL & 0x00FFFFFF) >>> 16)).append(".");
        sb.append(String.valueOf((ipL & 0x0000FFFF) >>> 8)).append(".");
        sb.append(String.valueOf(ipL & 0x000000FF));
        return sb.toString();
    }

    public static long ip2Long(String ip) {
        String[] ips = ip.split("\\.");
        return 256*256*256*Long.parseLong(ips[0])
                + 256*256*Long.parseLong(ips[1])
                + 256*Long.parseLong(ips[2])
                + Long.parseLong(ips[3]);
    }


    /**
     * 获取本月时间戳范围
     * @return
     */
    public static List<Long> getTimeRangeMonth() {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        range.add(calendar.getTimeInMillis());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        range.add(calendar.getTimeInMillis());

        return range;
    }

    /**
     * 获取每个月的时间戳范围
     */
    public static List<Long> getTimeRangeSingleMonth(Integer month){
        List<Long> range = new ArrayList<>();
        //月初
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        range.add(calendar.getTimeInMillis());
        //月末
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        range.add(calendar.getTimeInMillis());
        return range;
    }

    /**
     * 获取本周时间戳范围
     * @return
     */
    public static List<Long> getTimeRangeWeek() {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        range.add(calendar.getTimeInMillis());

        calendar.add(Calendar.DAY_OF_WEEK, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        range.add(calendar.getTimeInMillis());

        return range;
    }

    /**
     * 获取上周的时间戳
     * @return
     */
    public static List<Long> getTimeRangeLastWeek(){
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        range.add(calendar.getTimeInMillis() - 24*7*1000*60*60);
        range.add(calendar.getTimeInMillis() - 1);

        return range;
    }

    /**
     * 获取本天时间戳范围
     * @return
     */
    public static List<Long> getTimeRangeDay() {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        range.add(calendar.getTimeInMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        range.add(calendar.getTimeInMillis());

        return range;
    }

    /**
     * 获取本小时时间戳范围
     * @return
     */
    public static List<Long> getTimeRangeHour() {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        range.add(calendar.getTimeInMillis());

        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        range.add(calendar.getTimeInMillis());

        return range;
    }

    /**
     * 获取最近3小时
     * @return
     */
    public static List<Long> getTimeRangeLastHour3() {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Long timestamp = calendar.getTimeInMillis();
        range.add(timestamp - 10800000L);
        range.add(timestamp);

        return range;
    }

    /**
     * 获取最近12小时
     * @return
     */
    public static List<Long> getTimeRangeLastHour12() {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Long timestamp = calendar.getTimeInMillis();
        range.add(timestamp - 43200000L);
        range.add(timestamp);

        return range;
    }

    /**
     * 最近n天
     * @return
     */
    public static List<Long> getTimeRangeLastDay(int num) {
        List<Long> range = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Long timestamp = calendar.getTimeInMillis();
        range.add(timestamp - 86400000L * num);
        range.add(timestamp);

        return range;
    }




    /**
     * 获取session选择时间
     * @param session
     * @return
     */
    public static String getDateSelected(HttpSession session) {
        return (session.getAttribute("dateSelected") == null) ? "9" : (String)session.getAttribute("dateSelected");
    }
    /**
     * 根据字段名获取session选择时间
     * @param session
     * @return
     */
    public static String getDateText(HttpSession session, String dateTxt) {
        return (session.getAttribute(dateTxt) == null) ? "9" : (String)session.getAttribute(dateTxt);
    }

    /**
     * 获取时间范围
     * @param datetype 时间类型
     * @return 时间戳数组
     */
    public static List<Long> getTimeRange(String datetype) {
        if (datetype.equals("9")) {//本小时
            return getTimeRangeHour();
        }
        else if (datetype.equals("10")) {//本天
            return getTimeRangeDay();
        }
        else if (datetype.equals("11")) {//本周
            return getTimeRangeWeek();
        }
        else if (datetype.equals("15")) {//上周
            return getTimeRangeLastWeek();
        }
        else if (datetype.equals("12")) {//本月
            return getTimeRangeMonth();
        }
        else if (datetype.equals("16")) {//1月
            return getTimeRangeSingleMonth(1);
        }
        else if (datetype.equals("17")) {//2月
            return getTimeRangeSingleMonth(2);
        }
        else if (datetype.equals("18")) {//3月
            return getTimeRangeSingleMonth(3);
        }
        else if (datetype.equals("19")) {//4月
            return getTimeRangeSingleMonth(4);
        }
        else if (datetype.equals("20")) {//5月
            return getTimeRangeSingleMonth(5);
        }
        else if (datetype.equals("21")) {//6月
            return getTimeRangeSingleMonth(6);
        }
        else if (datetype.equals("22")) {//7月
            return getTimeRangeSingleMonth(7);
        }
        else if (datetype.equals("23")) {//8月
            return getTimeRangeSingleMonth(8);
        }
        else if (datetype.equals("24")) {//9月
            return getTimeRangeSingleMonth(9);
        }
        else if (datetype.equals("25")) {//10月
            return getTimeRangeSingleMonth(10);
        }
        else if (datetype.equals("26")) {//11月
            return getTimeRangeSingleMonth(11);
        }
        else if (datetype.equals("27")) {//12月
            return getTimeRangeSingleMonth(12);
        }
        else if (datetype.equals("13")) {//最近3小时
            return getTimeRangeLastHour3();
        }
        else if (datetype.equals("14")) {//最近12小时
            return getTimeRangeLastHour12();
        }
        else if (datetype.contains(",")) {//自定义
            String[] array = datetype.split(",");
            List<Long> range = new ArrayList<>();
            for (String str: array) {
                range.add(dateToLong(str));
            }
            return range;
        }
        return null;
    }

    //获取一些时间段，用于统计显示，getTimeList开头的函数。
    /**
     * 获取一天的时间段，以小时为单位。
     * @return
     */
    public static List<List<Long>> getTimeListDay() {
        List<List<Long>> list = new ArrayList<>();
        long begin, end;
        Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.HOUR_OF_DAY, 1);
        end = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        begin = calendar.getTimeInMillis();

        for (long index=begin; index<end; index+=1000) {
            List<Long> range = new ArrayList<>();
            range.add(index);

            index += (3600-1)*1000;
            range.add(index);
            list.add(range);
        }

        return list;
    }

    /**
     * 获取最近三天的时间段，以天为单位。
     * @return
     */
    public static List<List<Long>> getTimeListDay3() {
        List<List<Long>> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -2);

        List<Long> range = new ArrayList<>();//前两天
        range.add(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, 1);
        range.add(calendar.getTimeInMillis());
        list.add(range);

        range = new ArrayList<>();//前一天
        range.add(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, 1);
        range.add(calendar.getTimeInMillis());
        list.add(range);

        range = new ArrayList<>();//当天
        range.add(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, 1);
        range.add(calendar.getTimeInMillis());
        list.add(range);

        return list;
    }

    /**
     * 获取最近一周的时间段，以天为单位。
     * @return
     */
    public static List<List<Long>> getTimeListWeek() {
        List<List<Long>> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -6);

        List<Long> range = null;
        for (int i=0; i<7; i++) {
            range = new ArrayList<>();
            range.add(calendar.getTimeInMillis());
            calendar.add(Calendar.DATE, 1);
            range.add(calendar.getTimeInMillis());
            list.add(range);
        }

        return list;
    }

    /**
     * 获取最近一月的时间段，以周为单位。
     * @return
     */
    public static List<List<Long>> getTimeListMonth() {
        List<List<Long>> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -29);

        List<Long> range = null;
        for (int i=0; i<30; i++) {
            range = new ArrayList<>();
            range.add(calendar.getTimeInMillis());
            calendar.add(Calendar.DATE, 1);
            range.add(calendar.getTimeInMillis());
            list.add(range);
        }

        return list;
    }

    /**
     * 获取最近一年的时间段，以月为单位。
     * @return
     */
    public static List<List<Long>> getTimeListYear() {
        List<List<Long>> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -11);
        calendar.add(Calendar.DAY_OF_YEAR, -15);

        List<Long> range = null;
        for (int i=0; i<24; i++) {
            range = new ArrayList<>();
            range.add(calendar.getTimeInMillis());
            calendar.add(Calendar.DAY_OF_YEAR, 15);
            range.add(calendar.getTimeInMillis());
            list.add(range);
        }

        return list;
    }

    /**
     * 获取最近两周的时间段，以天为单位。
     * @return
     */
    public static List<List<Long>> getTimeListWeek2() {
        List<List<Long>> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -13);

        List<Long> range = null;
        for (int i=0; i<14; i++) {
            range = new ArrayList<>();
            range.add(calendar.getTimeInMillis());
            calendar.add(Calendar.DATE, 1);
            range.add(calendar.getTimeInMillis());
            list.add(range);
        }

        return list;
    }

    /**
     * 获取报表时间范围
     * @param type 时间范围类型
     * @return
     */
    public static List<List<Long>> getTimeListRange(int type) {
        switch (type) {
            case 0://当天
                return getTimeListDay();
            case 1://最近三天
                return getTimeListDay3();
            case 2://最近一周
                return getTimeListWeek();
            case 3://最近一月
                return getTimeListMonth();
            case 4://最近一年
                return getTimeListYear();
            case 5://和上周对比
                return getTimeListWeek2();
            default:
                return new ArrayList<>();
        }
    }




    public static String md5Encode(String str) {
        MessageDigest md5;
        String newstr = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            newstr = org.apache.commons.codec.binary.Base64.encodeBase64String(md5.digest(str.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未找到MD5加密算法！", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("未找到MD5加密算法！", e);
        }
        return newstr.trim();
    }

    /**
     * mac地址转换，从byte[]数组转成字符串
     * @param mac
     * @return
     */
    public static String macByteToString(byte[] mac) {
        String strMac = "";
        for (int i=0; i<mac.length; i++) {
            if (i != 0) strMac += ":";
            strMac += String.format("%02x", mac[i] & 0xff);
        }
        return strMac;
    }

    /**
     * byte[]转换object
     */
    public static Object bytesToStringArray(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * byte[]转换object
     */
    public static byte[]  arrayTobyte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * Http获取ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public static boolean runCmd(String cmd){
        try {
            String[] cmds = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(cmds,null);
            int res = process.waitFor();
            if (res != 0) return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean testIP(String ip) throws IOException {
        boolean isIpReachable;
        InetAddress address = InetAddress.getByName(ip);
        isIpReachable = address.isReachable(3000);
        if (!isIpReachable){
            return false;
        }
        return true;
    }

    public static String getIpFromAddr(String httpAddr) {
        if (httpAddr == null || httpAddr.isEmpty()) {
            return "";
        }
        String strIp = "";
        if (httpAddr.contains("https")) {
            strIp = httpAddr.substring(8, httpAddr.length() - 5);
        } else if (httpAddr.contains("http")) {
            strIp = httpAddr.substring(7, httpAddr.length() - 5);
        }
        return strIp;
    }
}