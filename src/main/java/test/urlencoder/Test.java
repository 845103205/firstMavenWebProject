package test.urlencoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //java.net.URLEncoder对中文的编码和解码
        String str = URLEncoder.encode("飞舞", "utf-8");
        //String a = java.net.URLDecoder.decode(str, "UTF-8");
        System.out.println(  "--" + str);


    }
}
