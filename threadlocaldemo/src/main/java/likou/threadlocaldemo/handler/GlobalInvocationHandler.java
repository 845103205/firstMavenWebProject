package likou.threadlocaldemo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类说明: 动态代理熔断器返回类
 * @author BrandoLv 2020-01-13 8:55
 */
public class GlobalInvocationHandler implements InvocationHandler {

    public static Object newInstance(Class[] interfaces) {
        return Proxy.newProxyInstance(GlobalInvocationHandler.class.getClassLoader(), interfaces, new GlobalInvocationHandler());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.err.println("错误 熔断器-调用: " + method.getName() + " 时进入熔断保护!");
//        if(method.getReturnType().isInterface()) {
//            System.out.println("熔断器-返回接口类型:" + method.getReturnType().getName());
//            return null;
//        }
//        Object obj = method.getReturnType().newInstance();
//        System.out.println("熔断器-返回值类型:" + obj.getClass().toString());
//        if(obj instanceof BaseEntity) {
//            BaseEntity base = (BaseEntity)obj;
//            base.SERVICE_RETURN_CODE = 0;
//            return obj;
//        }
//        return null;
        throw new RuntimeException();
    }

}
