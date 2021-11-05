package likou.threadlocaldemo.core;

import lombok.Data;
import likou.threadlocaldemo.Logger.Logger;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 报表制作虚基类，每种报表制作类要实现自己的execute()方法。
 * Created by byc on 8/15/18.
 */
@Data
abstract public class Reporter {
    //LOGO图标
    private String logo = Reporter.class.getResource("/").getFile() + "static/logo.jpg";

    public static final String captionTitle = "报表名称";
    public static final String captionCreateTime = "制表时间";
    public static final String captionUserName = "制表人";
    public static final String captionCondition = "条件";
    protected String title;//报表名称
    protected String userName;//制表人
    protected String condition;//条件

    protected String fileName;//报表文件名称
    protected String suffName;//报表文件后缀名
    protected List<BlockBase> items = new ArrayList<>();//报表块内容
    protected OutputStream out = null;//输出流

    public static Reporter getInstance(ReportType type) {
        Reporter reporter = null;

        if (type == ReportType.WORD) {
            reporter = new ReporterWord();
            reporter.setSuffName(".rtf");
        }
        else if (type == ReportType.EXCEL) {
            reporter = new ReporterExcel();
            reporter.setSuffName(".xls");
        }
        else if (type == ReportType.PDF) {
            reporter = new ReporterPdf();
            reporter.setSuffName(".pdf");
        }
        else {
            Logger.error("报表类型错误！");
        }
        return reporter;
    }

    //报表导出接口
    public void export(HttpServletResponse response) {
        try {
            String strFileName = URLEncoder.encode(this.fileName+this.suffName, "UTF-8");
            response.encodeRedirectURL("UTF-8");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition", "attachment; filename=" + strFileName + "; " + "filename*=utf8''" + strFileName);
            this.out = response.getOutputStream();

            //报表制作
            this.execute();
            this.out.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("export file", e);
        }
    }

    //各种导出插件干自己的活就行了
    protected abstract void execute() throws Exception;

    public void addBlock(BlockBase blockBase) {
        this.items.add(blockBase);
    }

    public enum ReportType {
        WORD("WORD"),
        EXCEL("EXECL"),
        PDF("PDF");

        private String value;

        public static ReportType valueOf(int type) {
            switch (type) {
                case 0:
                    return WORD;
                case 1:
                    return EXCEL;
                case 2:
                    return PDF;
            }
            throw new RuntimeException("不支持的报表类型。type： " + type);
        }

        ReportType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
