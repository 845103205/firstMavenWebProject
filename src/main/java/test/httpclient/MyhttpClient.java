package test.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyhttpClient {
    private static String result;

    public static void main(String[] args) {
        URL realUrl = null;
        BufferedReader reader =null;
        try {
            realUrl = new URL("https://static.zhihu.com/heifetz/main.topstory-routes.216a26f4.db25b11c19fb1e9a1c84.css");
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");
            /*if (httpMethod.equals(POST) || httpMethod.equals(PUT)) {
                conn.setDoOutput(true);
                conn.setDoInput(true);
                writer = new PrintWriter(conn.getOutputStream());
                writer.print(param);
                writer.flush();
            }*/
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = reader.readLine())!=null){
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {

                if (reader != null){
                    reader.close();
                }

            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        System.out.println(result);

    }

}
