package likou.threadlocaldemo.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明: Ajax返回Data
 * @author BrandoLv 2021-07-16 13:56
 */
public class ResponseData {

    /** 返回编码, 1000 成功, 2222 失败, 9999 异常 1001 登录失效或未登录 **/
    public Integer code = 1000;

    /** 返回信息 **/
    public String msg;

    /** 返回数据 **/
    public Object data;

    /** 系统时间 **/
    public Long sysTime;

    /**
     * 方法说明: 放入返回数据.
     * @author BrandoLv 2021-07-16 13:59
     * @param key 返回数据Key
     * @param obj 返回对象
     * @return
     */
    public ResponseData putData(String key, Object obj) {
        if(data == null) {
            data = new HashMap<String, Object>();
        }
        if(data instanceof Map) {
            Map dataMap = (Map) data;
            dataMap.put(key, obj);
        }
        return this;
    }

    /**
     * 方法说明: 删除返回数据
     * @author BrandoLv 2021-07-16 14:26
     * @param key 返回数据Key
     */
    public ResponseData removeData(String key) {
        if(data != null && this.data instanceof Map) {
            Map dataMap = (Map) data;
            dataMap.remove(key);
        }
        return this;
    }

    /**
     * 方法说明: 设置错误返回代码.
     * @author BrandoLv 2021-07-16 14:01
     * @param code 错误返回编码.
     */
    public ResponseData setErrorCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 方法说明: 设置错误返回代码和提示信息.
     * @author BrandoLv 2021-07-16 14:24
     * @param code 错误返回编码.
     * @param msg 错误提示信息.
     * @return
     */
    public ResponseData setErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * 方法说明:
     * @author BrandoLv 2021-07-16 14:20
     * @param
     * @return
     */
    public ResponseData setErrorMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
