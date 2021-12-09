package likou.threadlocaldemo.tools;

import cn.hutool.core.util.StrUtil;

/**
 * 方法说明: 验证器
 * @author BrandoLv 2020-07-29 9:59
 */
public class Validator {


    /**
     * 方法说明: 密码验证
     * @author BrandoLv 2020-07-29 9:59
     * @return boolean 验证是否成功, true:成功, false:失败
     */
    public static boolean password(String pwd) {
        if(StrUtil.isBlank(pwd)) {
            return false;
        }
        if(pwd.length() >= 6 && pwd.length() <= 16) {
            return true;
        }
        return false;
    }



}
