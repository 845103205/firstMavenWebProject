package likou.threadlocaldemo.controller;

import likou.threadlocaldemo.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tanjian
 * @date 2021/12/6 9:54
 */
@Controller
@ResponseBody
public class UtilController {
    @Autowired
    private MyUtils myUtils;

    @RequestMapping("/util")
    public String getUtils(){
        return MyUtils.getCurrentProjectBasePath();
    }
}
