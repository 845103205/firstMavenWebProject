package likou.threadlocaldemo.tools.comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 集成数据类型集合
 * @Author: wx
 * @Date: 2020/12/24
 */
public class PropertyTypeUtil {

    private static final Set<Class<?>> primitiveWrapperSet = new HashSet<>(10);

    static {
        primitiveWrapperSet.add(Boolean.class);
        primitiveWrapperSet.add(Byte.class);
        primitiveWrapperSet.add(Character.class);
        primitiveWrapperSet.add(Double.class);
        primitiveWrapperSet.add(Float.class);
        primitiveWrapperSet.add(Integer.class);
        primitiveWrapperSet.add(Long.class);
        primitiveWrapperSet.add(Short.class);
    }

    /**
     * 是否基本属性
     * @param clazz
     * @return
     */
    public static boolean isSampleProperty(Class<?> clazz) {
        if (clazz == null) {
            throw new RuntimeException("class is null");
        }
        if (clazz.isPrimitive() || primitiveWrapperSet.contains(clazz) || clazz.isEnum()
                || CharSequence.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz)
                || Date.class.isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }
}
