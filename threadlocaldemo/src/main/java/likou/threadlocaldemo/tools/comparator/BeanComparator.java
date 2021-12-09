package likou.threadlocaldemo.tools.comparator;

import cn.hutool.core.util.StrUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: wx
 * @Date: 2020/12/24
 */
public class BeanComparator {
    /**
     * 获得对比结果
     * Obtain comparative results
     *
     * @param source
     * @param target
     * @return
     */
    public static String getCompareResult(Object source, Object target) {
        String result = compareBean(source, target);
        return result;
    }

    /**
     *  对象转换成字符串
     * @param source
     * @return
     */
    public static String getCompareResult(Object source) {
        String result = compareBean(source);
        return result;
    }

    /**
     * 获得对比结果，支持属性名称map，只对比map中包含的属性值
     * @param source                 改变前实体类
     * @param target                 改变后实体类
     * @param propertyTranslationMap 属性中文名集合
     * @param dataDictionaryMap      数据字典集合
     * @return
     */
    public static String getCompareResult(Object source, Object target, Map<String, String> propertyTranslationMap, Map<String, Map<String, String>> dataDictionaryMap) {
        String result = compareBean(source, target, propertyTranslationMap, dataDictionaryMap);
        return result;
    }

    /**
     * 对象转换成字符串
     * @param source
     * @param propertyTranslationMap
     * @param dataDictionaryMap
     * @return
     */
    public static String getCompareResult(Object source, Map<String, String> propertyTranslationMap, Map<String, Map<String, String>> dataDictionaryMap) {
        String result = compareBean(source, propertyTranslationMap, dataDictionaryMap);
        return result;
    }

    /**
     * 对比bean属性
     *
     * @param source
     * @param target
     * @return
     */
    public static String compareBean(Object source, Object target) {
        return compareBean(source, target, null, null);
    }

    /**
     * 属性转换
     * @param source 转换对象
     * @return
     */
    public static String compareBean(Object source) {
        return compareBean(source, null, null);
    }

    /**
     * 对比bean属性，支持属性名称map，只对比map中包含的属性值
     *
     * @param source                 修改前实体对象
     * @param target                 修改后实体对象
     * @param propertyTranslationMap 属性中文名集合
     * @param dataDictionaryMap      数据字典集合
     * @return
     */
    public static String compareBean(Object source, Object target, Map<String, String> propertyTranslationMap, Map<String, Map<String, String>> dataDictionaryMap) {
        if (source == null) {
            throw new RuntimeException("source is null.");
        }
        if (target == null) {
            throw new RuntimeException("target is null.");
        }
        if (!source.getClass().equals(target.getClass())) {
            throw new RuntimeException("source.class and target.class is not same.");
        }

        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();
        return compareProperty(source, target, clazz, fields, propertyTranslationMap, dataDictionaryMap);
    }

    public static String compareBean(Object source, Map<String, String> propertyTranslationMap, Map<String, Map<String, String>> dataDictionaryMap){
        StringBuilder sBuilder = new StringBuilder();
        if (source == null) {
            throw new RuntimeException("source is null.");
        }
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!PropertyTypeUtil.isSampleProperty(field.getType())  || "serialVersionUID".equals(field.getName())) {
                //todo 复杂类型属性暂不处理
                continue;
            }
            String name = field.getName();

            String sourcePropertyValue = getFormatStr(getValueByField(source, clazz, field), name, dataDictionaryMap);

            // 属性值翻译中文
            String nameMapped = null;
            if (!(propertyTranslationMap == null || propertyTranslationMap.isEmpty())) {
                if (!propertyTranslationMap.containsKey(name)) {
                    continue;
                } else {
                    nameMapped = propertyTranslationMap.get(name);
                }
            }

            if (nameMapped != null) {
                name = nameMapped;
            }
            if (StrUtil.isNotEmpty(sourcePropertyValue)){
                sBuilder.append(name + ":" + sourcePropertyValue + ",");
            }
        }
        return sBuilder.toString();
    }

    /**
     * 比较所有属性值，根据传参转换属性名称，组装结果（核心逻辑）
     *
     * @param source                 修改前实体对象
     * @param target                 修改后实体对象
     * @param clazz                  class 类
     * @param fields                 属性集合
     * @param propertyTranslationMap 属性中文名集合
     * @param dataDictionaryMap      数据字典集合
     * @return
     */
    private static String compareProperty(Object source, Object target, Class<?> clazz, Field[] fields,
                                          Map<String, String> propertyTranslationMap, Map<String, Map<String, String>> dataDictionaryMap) {
        StringBuilder sBuilder = new StringBuilder();
        for (Field field : fields) {
            if (!PropertyTypeUtil.isSampleProperty(field.getType()) || "serialVersionUID".equals(field.getName())) {
                //todo 复杂类型属性暂不处理

                continue;
            }
            String name = field.getName();
            String nameMapped = null;

            String sourcePropertyValue = getFormatStr(getValueByField(source, clazz, field), name, dataDictionaryMap);
            String targetPropertyValue = getFormatStr(getValueByField(target, clazz, field), name, dataDictionaryMap);

            if (!(propertyTranslationMap == null || propertyTranslationMap.isEmpty())) {
                if (!propertyTranslationMap.containsKey(name)) {
                    continue;
                } else {
                    nameMapped = propertyTranslationMap.get(name);
                }
            }

            Class<?> propertyClazz = field.getClass();
            if (nameMapped != null) {
                name = nameMapped;
            }

            comparePropertyValue(sBuilder, name, propertyClazz, sourcePropertyValue, targetPropertyValue);
        }
        return sBuilder.toString();
    }

    /**
     * 获取属性值
     *
     * @param obj
     * @param clazz
     * @param field
     * @return
     */
    private static Object getValueByField(Object obj, Class<?> clazz, Field field) {
        Object result = null;
        try {
            if (field.getGenericType().toString().equals("boolean")) {
                String methodName = field.getName();
                if (!methodName.startsWith("is")) {
                    methodName = "is" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                }
                Method method = clazz.getMethod(methodName);
                result = method.invoke(obj);

            } else {
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
                Method method = descriptor.getReadMethod();
                result = method.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    /**
     * 对比属性值
     *
     * @param sBuilder
     * @param name
     * @param propertyClazz
     * @param sourcePropertyValue
     * @param targetPropertyValue
     */
    private static void comparePropertyValue(StringBuilder sBuilder, String name, Class<?> propertyClazz,
                                             String sourcePropertyValue, String targetPropertyValue) {
        if (StrUtil.equals(name,"gid")){
            sBuilder.append(name + ":" + sourcePropertyValue + ",");
        }else if (!Objects.equals(sourcePropertyValue, targetPropertyValue)) {
            sBuilder.append(name + ":" + sourcePropertyValue + "->" + targetPropertyValue + ",");
        }
    }

    /**
     * 格式化属性
     *
     * @param propertyValue
     * @return
     */
    private static String getFormatStr(Object propertyValue, String name, Map<String, Map<String, String>> dataDictionaryMap) {
        if (propertyValue == null) {
            return "";
        }
        if (propertyValue instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) propertyValue);
        } else {
            if ( dataDictionaryMap != null && dataDictionaryMap.containsKey(name)) {
                // 存在数据字典集合
                Map<String, String> dataDictionary = dataDictionaryMap.get(name);
                return dataDictionary.get(propertyValue);
            }
            return propertyValue.toString();
        }
    }
}
