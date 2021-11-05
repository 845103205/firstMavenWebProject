package likou.threadlocaldemo;

import likou.threadlocaldemo.ExportService.ExportService;
import likou.threadlocaldemo.asyncthread.executeTask;
import likou.threadlocaldemo.common.ImageToString;
import likou.threadlocaldemo.core.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class AsyncController {
    @Autowired
    private executeTask executeTask;
    /**
     * 导出各种格式的文件 word、excel、pdf。。。
     * @param type 文件类型
     * @param response 响应对象
     * @return
     */
    @RequestMapping(value = "async",method = RequestMethod.GET)
    public void export(int type, HttpServletResponse response) throws ExecutionException, InterruptedException {

        List<Future> results = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            results.add(executeTask.executeTask(i + 1));
        }
        System.out.println("--------------------------------");
        for (Future result : results) {
            Object o = result.get();
            System.out.println(o);
        }
    }


}
