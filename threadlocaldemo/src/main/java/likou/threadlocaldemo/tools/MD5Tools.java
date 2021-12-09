package likou.threadlocaldemo.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 类说明: MD5工具类.
 * @author Lyq 2015-6-12 下午2:26:50
 */
public class MD5Tools {
	
	 // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String getMD5Code(String strObj) {
        try {
            byte[] b_gbk = strObj.getBytes("utf-8");
            String resultString = null;
            try {
                resultString = new String(b_gbk,"utf-8");
                MessageDigest md = MessageDigest.getInstance("MD5");
                // md.digest() 该函数返回值为存放哈希值结果的byte数组
                resultString = byteToString(md.digest(strObj.getBytes()));
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            return resultString;
        }catch (Exception e){
            return null;
        }

    }
    
    public static void main(String[] args) {
		String pwd = getMD5Code(getMD5Code("123456") + "7zPR5d");
//		String pwd2 = getMD5Code("admin");

		System.out.println(pwd);
//		System.out.println(pwd2);

		String pwd2 = getMD5Code(pwd);
		System.out.println(pwd2);
	}

}
