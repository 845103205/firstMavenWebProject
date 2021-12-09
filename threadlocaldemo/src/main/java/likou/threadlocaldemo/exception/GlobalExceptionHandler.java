package likou.threadlocaldemo.exception;


import likou.threadlocaldemo.entity.ResponseData;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * 类说明: 全局异常处理类
 * @author BrandoLv 2021-02-09 11:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 方法说明: 全局异常处理
     * @author BrandoLv 2021-02-22 10:50
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData exceptionHandler(Exception e) {
        e.printStackTrace();
        ResponseData data = new ResponseData();
        data.code = 9999;
        data.msg = "系统异常, 请联系管理员!";
        data.data = e.getMessage();
        return data;
    }

    /**
     * 方法说明: 全局异常处理
     * @author BrandoLv 2021-02-22 10:50
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseData exceptionHandler1(Exception e) {
        e.printStackTrace();
        ResponseData data = new ResponseData();
        data.code = 8888;
        data.msg = "系统异常, 请联系管理员!";
        data.data = e.getMessage();
        return data;
    }

    /**
     * 方法说明: 全局异常处理
     * @author BrandoLv 2021-02-22 10:50
     */
    @ModelAttribute()
    public ResponseData myData() {
        ResponseData data = new ResponseData();
        data.code = 8888;
        data.msg = "系统异常, 请联系管理员!";
        return data;
    }

    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }


}
