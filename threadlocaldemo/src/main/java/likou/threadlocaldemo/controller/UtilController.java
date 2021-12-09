package likou.threadlocaldemo.controller;

import likou.threadlocaldemo.entity.Book;
import likou.threadlocaldemo.entity.Student;
import likou.threadlocaldemo.util.MyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author tanjian
 * @date 2021/12/6 9:54
 */
@Controller
@ResponseBody
public class UtilController {

    @RequestMapping("/util")
    public String getUtils(Model model, @ModelAttribute("b") Book book,@ModelAttribute("a") Student student){
        Map<String, Object> stringObjectMap = model.asMap();
        System.out.println(stringObjectMap);
        return MyUtils.getCurrentProjectBasePath();
    }
}
