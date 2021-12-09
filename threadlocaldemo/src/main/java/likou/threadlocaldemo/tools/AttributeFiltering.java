package likou.threadlocaldemo.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;

/**
 * 返回值属性过滤，不返回多余属性
 * @Author: wx
 * @Date: 2020/10/16
 */
public class AttributeFiltering {

    /**
     * 实体对象集合属性过滤
     * @param obj
     * @return
     */
    public static Object filteringSet(Object obj,String ... str){
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter includefilter = filters.addFilter();
        includefilter.addIncludes(str);
        String arr = JSONArray.toJSONString(obj,includefilter, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        return JSONArray.parse(arr);
    }

    /**
     * 实体对象属性过滤
     * @param obj
     * @return
     */
    public static Object filteringObj(Object obj,String[] includeProperties){
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter includefilter = filters.addFilter();
        includefilter.addIncludes(includeProperties);
        String arr = JSONObject.toJSONString(obj,includefilter, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        return JSONObject.parse(arr);
    }
}
