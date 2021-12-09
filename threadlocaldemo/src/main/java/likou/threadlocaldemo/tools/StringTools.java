package likou.threadlocaldemo.tools;

import cn.hutool.core.util.ObjectUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * 类说明: 字符串工具类
 * @author xiao天__ 2015-3-23 下午03:39:59
 */
public class StringTools {

	/**存储名字生成字符串**/
	public static char[] charArray = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	};

	public static String[] stringArray = new String[] {
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};

	/**
	 * 方法说明: 判断字符串只有英文和数字，且长度为length
	 * @author luomi 2020/11/16 17:18
	 */
   public static Boolean isEn(String str,int length){
		if(str.length()>length){
			return false;
		}
	   return isEnglish(str);
   }
	public static boolean isEnglish(String charaString){
		return charaString.matches("^[a-z]*");

	}

	public static Boolean isNum(String str){
		//判断是否是数字
		try{
			Integer.parseInt(str);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}
	/**
	 *
	 * 方法说明: 字符串是否为空,
	 * @author xiao天__ 2015-3-23 下午03:41:29
	 * @param str
	 * @return Boolean true:为空, false:不为空
	 */
	public static Boolean isNull(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static Boolean isNotNull(String str) {
		if(str != null && !"".equals(str.trim())) {
			return true;
		}
		return false;
	}
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}

		if (obj instanceof String) {
			return ((String) obj).equalsIgnoreCase("null")
					| ((String) obj).trim().toString().equals("");
		}

		if (obj instanceof StringBuffer) {
			return ((StringBuffer) obj).length() == 0;
		}

		if (obj.getClass().isArray()) {
			try {
				Object[] a = (Object[]) obj;

				boolean b = true;
				for (Object o : a) {
					b = b & isEmpty(o);

					if (!b) {
						break;
					}
				}

				return b;
			} catch (ClassCastException e) {
			}
		}
		return false;
	}
	/**
	 *
	 * 方法说明: string转换成日期.
	 * @author xiao天__ 2015-3-23 下午05:04:42
	 * @param str yyyy-MM-dd类型的日期格式. 例: 1987-05-15
	 * @return Date 日期
	 * @throws ParseException
	 */
	public static Date toDateyyyyMMdd(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(str);
	}

	/**
	 *
	 * 方法说明: 字符串转日期.
	 * @author Lyq 2016-8-12 下午3:28:17
	 * @param str 日期字符串 格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Date toDateyyyyMMddHHmmss(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(str);
	}

	/**
	 *
	 * 方法说明: 字符串转日期 没有符号
	 * @author Lyq 2017-2-8 下午2:47:10
	 * @param str 日期字符串 格式 yyyyMMddHHmmss
	 * @return
	 * @throws ParseException
	 */
	public static Date toDateyyyyMMddHHmmssNotSign(String str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.parse(str);
	}

	/**
	 *
	 * 方法说明: string转换成日期.
	 * @author xiao天__ 2015-3-23 下午05:04:42
	 * @param date yyyy-MM-dd类型的日期格式. 例: 1987-05-15
	 * @return Date 日期
	 * @throws ParseException
	 */
	public static String datetimetoString_CH(Date date){
		if (date != null) {
			SimpleDateFormat init = new SimpleDateFormat("yyyy年MM月dd日");
			init.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			return init.format(date);
		}
		return null;
	}

	/**
	 *
	 * 方法说明: 把日期时间，转化成字符串
	 * @author Lyq 2015-5-9 上午11:12:59
	 * @param date
	 * @return String 返回yyyy-MM-dd hh:mm:ss日期格式
	 */
	public static String DatetimetoString(Date date) {
		if (date != null) {
			SimpleDateFormat init = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			init.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			return init.format(date);
		}
		return null;
	}

	/**
	 *
	 * 方法说明: 把日期时间，转化成字符串
	 * @author Lyq 2017-2-8 下午5:35:58
	 * @param date
	 * @return
	 */
	public static String DatetimetoStringNotSign(Date date) {
		if (date != null) {
			SimpleDateFormat init = new SimpleDateFormat("yyyyMMddHHmmss");
			init.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			return init.format(date);
		}
		return null;
	}

	public static String date2String(Date date) {
		SimpleDateFormat init = new SimpleDateFormat("yyyyMMdd");
		init.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		return init.format(date);
	}

	/**
	 *
	 * 方法说明: 通过邮箱. 获取邮箱登录地址.
	 * @author Lyq 2015-6-24 下午5:38:15
	 * @param email 邮箱地址.
	 * @return String 邮箱登录地址.
	 */
	public static String getEmailLoginHref(String email) {
		return "mail." + email.substring(email.indexOf("@")+1);
	}

	/**
	 *
	 * 方法说明: 把日期时间，转化成字符串
	 * @author Lyq 2015-5-9 上午11:12:59
	 * @param date
	 * @return String 返回yyyy-MM-dd日期格式
	 */
	public static String dateToStringyyyyMMddHH(Date date) {
		if (date != null) {
			SimpleDateFormat init = new SimpleDateFormat("yyyy-MM-dd HH");
			return init.format(date);
		}
		return null;
	}

	/**
	 *
	 * 方法说明: 把日期时间，转化成字符串
	 * @author Lyq 2015-5-9 上午11:12:59
	 * @param date
	 * @return String 返回yyyy-MM-dd日期格式
	 */
	public static String dateToStringyyyyMMdd(Date date) {
		if (date != null) {
			SimpleDateFormat init = new SimpleDateFormat("yyyy-MM-dd");
			return init.format(date);
		}
		return null;
	}

	/**
	 *
	 * 方法说明: 获取随机盐值.
	 * @author Lyq 2015-10-16 上午9:50:27
	 * @return
	 */
	public static String getRandomSalt() {
		String[] array = new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
				};
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int index = (int)(Math.random()*(array.length));
			buf.append(array[index]);
		}
		return buf.toString();
	}

	/**
	 *
	 * 方法说明: 获取4位长度验证码
	 * @author LvYunqingS 2017-1-13 下午4:07:51
	 * @return
	 */
	public static String getValidateCode() {
		String[] array = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			int index = (int)(Math.random()*(array.length));
			buf.append(array[index]);
		}
		return buf.toString();
	}

	/**
	 *
	 * 方法说明: 获取当前时间秒数, 不要毫秒.
	 * @author Lyq 2015-10-16 上午10:29:49
	 * @return
	 */
	public static Long getCurrentDateTimeStr() {
		String dateTime = System.currentTimeMillis()+"";
		return Long.parseLong(dateTime.substring(0, 10));
	}

	public static long convertDateStringToTimestamp(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = 0;
		try {
			Date date = sdf.parse(dateString);
			time = date.getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 *
	 * 方法说明: 获取模型编号
	 * @author Lyq 2017-1-8 下午6:38:41
	 * @return
	 */
	public static String getModelNo() {
		String result = UUID.randomUUID().toString();
		result = result.replaceAll("-", "");
		return result;
	}

	/**
	 *
	 * 方法说明: 获取YYID
	 * @author LvYunqingS 2017-1-9 下午12:02:40
	 * @return
	 */
	public static String getUUID() {
		String result = UUID.randomUUID().toString();
		result = result.replaceAll("-", "");
		return result;
	}
	/**
	 *
	 * 方法说明:  获取代金券编码
	 * @author lm 2017-3-17 下午2:06:09
	 * @return
	 */
	public static String getCashCouponInfoNo() {
		String result = UUID.randomUUID().toString();
		result = result.replaceAll("-", "");
		return result;
	}

	public static void main(String[] args) {
//		char[] cs = "a22drtfwwfw我".toCharArray();
//		Boolean result = Arrays.asList(stringArray).contains(String.valueOf(cs[11]));
//		System.out.println(result);

//		String source = "";
//		for (int i = 0; i < charArray.length; i++) {
//			Character a = charArray[i];
//			for (int j = 0; j < charArray.length; j++) {
//				Character b = charArray[j];
//				for (int k = 0; k < charArray.length; k++) {
//					Character c = charArray[k];
//					source = a.toString() + b.toString() + c.toString();
//					Integer number = StringTools.stringToNumber(source);
//					String str = StringTools.numberToString(number);
//					if(!source.equals(str)) {
//						System.out.println("source:" + source + " number:" + number + " str:" + str);
//					}
//				}
//			}
//		}
//		source = "101";
//		int number = stringToNumber(source);
//		String str = numberToString(number);
//		System.out.println("source:" + source + " number:" + number + " str:" + str);
		System.out.println(numberToStringTwo(2));
	}

	/**
	 * 方法说明: 数字转字符.
	 * @author BrandoLv 2020-08-17 11:05
	 */
	public static String numberToString(int num) {
		//最大三位验证.
		if(num < 0 || num >= charArray.length*charArray.length*charArray.length) {
			return null;
		}
		//指针，从数组最后开始
		char[] resArray = { charArray[0], charArray[0], charArray[0] };
		int pos = resArray.length;
		int radix = charArray.length;

		//开始循环计算num和radix的商和余数
		while(num > 0)
		{
			resArray[--pos] = charArray[num % radix];
			num /= radix;
		}
		String str = "";
		for (int i = 0; i < resArray.length; i++) {
			str += resArray[i];
		}
		return str;
	}

	/**
	 * 方法说明: 数字转字符.
	 * @author BrandoLv 2020-08-17 11:05
	 */
	public static String numberToStringTwo(int num) {
		//最大三位验证.
		if(num < 0 || num >= charArray.length*charArray.length) {
			return null;
		}
		//指针，从数组最后开始
		char[] resArray = { charArray[0], charArray[0]};
		int pos = resArray.length;
		int radix = charArray.length;

		//开始循环计算num和radix的商和余数
		while(num > 0)
		{
			resArray[--pos] = charArray[num % radix];
			num /= radix;
		}
		String str = "";
		for (int i = 0; i < resArray.length; i++) {
			str += resArray[i];
		}
		return str;
	}


	/**
	 * 方法说明: 编号转为字符串.
	 * @author BrandoLv 2020-08-17 9:45
	 */
	public static String indexToString(int index) {
		if(0 <= index && index < charArray.length) {
			return charArray[index] + "";
		}
		return null;
	}


	/**
	 * 方法说明: 字符转数字.
	 * @author BrandoLv 2020-08-17 11:05
	 */
	public static Integer stringToNumber(String str) {
		if(str.length() != 3) {
			return null;
		}
		// 倍数
		int multiple = 1;
		//返回值
		int result = 0;
		Character c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(str.length() - i - 1);
			result += decodeChar(c) * multiple;
			multiple = multiple * charArray.length;
		}
		return result;
	}

	private static int decodeChar(Character c) {
		for (int i = 0; i < charArray.length; i++) {
			if (c == charArray[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 方法说明: 生成一个流水号
	 * @author luomi 2020/3/20 9:54
	 */
	public static final Long next() {
		String time = DateTimeTools.format(new Date(), DateTimeTools.FormatType.MICROSECONDSID);
		String queuing = time + getNum(1);
		return Long.parseLong(queuing);
	}
	/**
	 * 生成一个num位的随机整数
	 */
	public static String getNum(Integer num) {
		String str = "";
		for (int i = 0; i < num; i++) {
			str += new Random().nextInt(9);
		}
		return str;
	}

	/**
	 * 方法说明: 字符串转Float类型.
	 * @author BrandoLv 2020-06-03 14:27
	 * @param val 需要转转的字符串.
	 * @return	Float 转换出来的值. 如果不能转换则返回为空.
	 */
	public static Float stringConvertFloat(String val) {
		try {
			Float v = Float.parseFloat(val);
			return v;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Description: 转换值为正整数并抹去小数点后面的0
	 * @param val
	 * @return java.lang.String
	 * @author wx 2021/4/22
	 */
	public static String transformAbs(Object val){
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		double str = Math.abs(Double.parseDouble(String.valueOf(val)));
		return decimalFormat.format(str);
	}

	/**
	 * @Description: 抹去小数点后面的0
	 * @param val
	 * @return java.lang.String
	 * @author wx 2021/4/22
	 */
	public static String transform(Object val){
		if (ObjectUtil.isNotEmpty(val)){
			DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
			double str = Double.parseDouble(String.valueOf(val));
			return decimalFormat.format(str);
		}
		return "";
	}

	/**
	 * 方法说明: 获取 0 到 小于max 的随机数
	 * @author BrandoLv 2021-05-16 15:10
	 */
	public static int getRandom(int max) {
		return (int)(Math.random() * max);
	}
}
