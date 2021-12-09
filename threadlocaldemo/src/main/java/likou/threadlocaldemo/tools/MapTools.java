package likou.threadlocaldemo.tools;

/**
 * 类说明: 地图工具类.
 * @author BrandoLv 2021-01-04 9:31
 */
public class MapTools {

    private static float EARTH_RADIUS = 6378.137f;

    private static float rad(float d) {
        return d * (float)Math.PI / 180.0f;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return 距离
     */
    public static float getDistance(float lat1, float lng1, float lat2, float lng2) {
        float radLat1 = rad(lat1);
        float radLat2 = rad(lat2);
        float a = radLat1 - radLat2;
        float b = rad(lng1) - rad(lng2);
        float s = 2 * (float)Math.asin((float)Math.sqrt((float)Math.pow((float)Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000f) / 10000f;
        s = s * 1000;
        return s;
    }

    public static void main(String[] args) {
        float distance = getDistance(29.505781f, 106.580243f, 29.504570f, 106.578412f);
        System.out.println("距离" + distance + "米");
    }


}
