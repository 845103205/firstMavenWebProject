package likou.threadlocaldemo.ExportService;

import com.alibaba.fastjson.JSONObject;
import likou.threadlocaldemo.common.Helpers;
import likou.threadlocaldemo.core.BlockEChart;
import likou.threadlocaldemo.core.BlockTable;
import likou.threadlocaldemo.core.Reporter;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportService {

    /**
     * 数据分布导出
     * @param response
     * @param type 报表类型
     * @param id 表格类型ID
     * @param dateType 时间类型
     * @param strPie 饼图内存
     * @param strBar 柱状图内存
     * @throws Exception
     */
    public void exportData(HttpServletResponse response, Reporter.ReportType type, String id, String dateType, String strPie, String strBar) throws Exception {
        Reporter reporter = Reporter.getInstance(type);
        //报表头
        reporter.setFileName("数据分布");
        reporter.setTitle("数据分布");
        reporter.setUserName("谭剑");
        List<Long> time = Helpers.getTimeRange(dateType);
        String strTime = Helpers.dateFormat(time.get(0)) + " ~ " + Helpers.dateFormat(time.get(1));
        reporter.setCondition(strTime);

        //报表数据
        List<JSONObject> result = new ArrayList<>();
        List<String> head = new ArrayList<>();
        String desc = "";
        switch (id) {
            case "access":{
                desc = "敏感数据类型访问量分布";
                head.add("类型");
                head.add("数据访问量");
                break;
            }
            case "table":{

                desc = "敏感表访问数据量";
                head.add("表名");
                head.add("数据访问量");
                break;
            }
            case "often":{

                desc = "敏感表访问次数";
                head.add("表名");
                head.add("访问次数");
                break;
            }
            case "type": {
                desc = "敏感数据类型静态分布";
                head.add("类型");
                head.add("数据量");
                break;
            }
        }
        reporter.addBlock(new BlockEChart(desc, strPie));
        reporter.addBlock(new BlockEChart(desc, strBar));

        List<List<String>> rows = new ArrayList<>();
        result.forEach((item) -> {
            List<String> row = new ArrayList<>();
            row.add(item.getString("name"));
            row.add(item.getString("value"));
            rows.add(row);
        });
        BlockTable table = new BlockTable(desc, head, rows);
        reporter.addBlock(table);

        //制作报表
        reporter.export(response);
    }

}
