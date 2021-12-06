package likou.threadlocaldemo.controller;

import likou.threadlocaldemo.ExportService.ExportService;
import likou.threadlocaldemo.common.ImageToString;
import likou.threadlocaldemo.core.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
public class ExportController {
    @Autowired
    private ExportService exportService;
    /**
     * 导出各种格式的文件 word、excel、pdf。。。
     * @param type 文件类型
     * @param response 响应对象
     * @return
     */
    @RequestMapping(value = "export",method = RequestMethod.GET)
    public void export(int type, HttpServletResponse response) {

        try {
            exportService.exportData(response,Reporter.ReportType.valueOf(type),"access","9",this.getImageStr1(),this.getImageStr2());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getImageStr1(){
        String logo = Reporter.class.getResource("/").getFile() + "static/logo.jpg";

        return ImageToString.imageToString(logo);
    }

    private String getImageStr2(){
        String logo = Reporter.class.getResource("/").getFile() + "static/u1936_disabled.png";

        return ImageToString.imageToString(logo);
    }

}
