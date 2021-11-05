package likou.threadlocaldemo.core;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import likou.threadlocaldemo.Logger.Logger;
import likou.threadlocaldemo.common.Helpers;
import sun.misc.BASE64Decoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * 制作PDF格式报表
 * Created by byc on 8/15/18.
 */
public class ReporterPdf extends Reporter {
    //最小行高
    private static final int MIN_ROW_HEIGHT = 28;
    //前后间距
    private static final int SPACING_BEFORE = 10;
    private static final int SPACING_AFTER = 10;
    private static String fontPath = "/usr/share/fonts/default/simsun.ttc,1";
    private static final String tmpFilePath = "/tmp/test.pdf";

    //标题颜色
    private final BaseColor color_title = new BaseColor(51, 122, 183);
    //行颜色
    private final BaseColor color_row = new BaseColor(249, 249, 249);

    private Font font_normal;
    private Font font_bold;
    private Document document;

    public ReporterPdf() {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            Logger.error("ReporterPdf", e);
        }

        font_normal = new Font(baseFont);
        font_bold = new Font(baseFont, Font.UNDEFINED, Font.BOLD);
    }

    //需要实现的报表制作方法
    @Override
    protected void execute() throws Exception {
        Rectangle rectangle = new Rectangle(PageSize.A4.rotate());
        rectangle.setBorderColor(BaseColor.BLACK);
        this.document = new Document(rectangle);
        PdfWriter writer = PdfWriter.getInstance(this.document, new FileOutputStream(ReporterPdf.tmpFilePath));
        //writer.setCloseStream(false);//是否关闭输出流
        writer.setBoxSize("art", rectangle);

//        //页眉 页脚
//        writer.setPageEvent(new PdfPageEventHelper() {
//            @Override
//            public void onEndPage(PdfWriter writer, Document document) {
//                if (writer.getPageNumber() != 1) {// 封面不需要页眉跟页脚
//                    PdfContentByte cb = writer.getDirectContent();
//                    Phrase footer = new Phrase(String.format("第%d页", writer.getPageNumber() - 1), font_normal);
//                    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
//                            (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
//
//                }
//            }
//        });

        this.document.open();

        //1 添加封面
        this.addCover();

        //页眉 目录
        //2 正文：按照提供的对象进行图表和表格的绘制
        for (BlockBase block : items) {
            if (block.type == BlockBase.BlockType.TABLE) {
                //表格的绘制
                BlockTable table = (BlockTable)block;
                this.plotTable(table);
            }
            else if (block.type == BlockBase.BlockType.ECHART) {
                //图表的形式绘制
                BlockEChart echart = (BlockEChart)block;
                this.plotEChart(echart);
            }
        }

        this.document.close();
        writer.close();

        /**
         * pdf文件导出时，直接下载pdf超级慢；所以用了首先生成到本地文件，然后下载文件的方式。
         */
        FileInputStream inputStream = new FileInputStream(ReporterPdf.tmpFilePath);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buf)) > 0) {
            this.out.write(buf, 0, len);
        }
        inputStream.close();
    }

    //绘制封面
    void addCover() throws Exception {
        Image logo = Image.getInstance(this.getLogo());
        logo.setAlignment(Image.ALIGN_CENTER);
        document.add(logo);

        PdfPTable table = new PdfPTable(new float[]{1, 3});
        table.setSpacingBefore(50);

        BiConsumer<String, String> f = (caption, content) -> {
            PdfPCell cell = newCell(caption);
            cell.setBackgroundColor(color_row);
            table.addCell(cell);
            cell = newCell(content);
            table.addCell(cell);
        };

        f.accept(Reporter.captionTitle, this.getTitle());
        f.accept(Reporter.captionUserName, this.getUserName());
        f.accept(Reporter.captionCreateTime, Helpers.dateNowFormat());
        f.accept(Reporter.captionCondition, this.getCondition());

        document.add(table);
    }

    private PdfPCell newCell(String content) {
        return newCell(content, font_normal);
    }

    private PdfPCell newCell(String content, Font font) {
        PdfPCell titleCell = new PdfPCell(new Phrase(content, font));
        titleCell.setMinimumHeight(MIN_ROW_HEIGHT);
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        return titleCell;
    }

    //绘制表格
    private void plotTable(BlockTable block) throws DocumentException {
        this.document.newPage();
        //添加一个描述信息
        Phrase phrase = new Phrase(block.getDesc(), font_bold);
        this.document.add(phrase);

        //添加一个表格
        int cols = block.getTitleCols().size();
        PdfPTable table = new PdfPTable(cols);
        // 设置表格宽度与对齐
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        // 表头
        for (String headerRow : block.getTitleCols()) {
            PdfPCell head = newCell(headerRow, font_bold);
            head.setBackgroundColor(color_title);
            table.addCell(head);
        }
        table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置前后间距
        table.setSpacingBefore(SPACING_BEFORE);
        table.setSpacingAfter(SPACING_AFTER);

        //表格的数据项
        if (block.getRows().size() > 0) {
            int index = 0;

            for (java.util.List<String> row : block.getRows()) {
                for (String r : row) {
                    PdfPCell cell = newCell(r, font_normal);
                    //表格的行间隔突出显示
                    if (index % 2 == 0) {
                        cell.setBackgroundColor(BaseColor.WHITE);
                    } else {
                        cell.setBackgroundColor(color_row);
                    }
                    table.addCell(cell);
                }

                index++;
            }
        }
        else {
            PdfPCell cell = newCell("无数据", font_normal);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setColspan(cols);
            table.addCell(cell);
        }

        this.document.add(table);
    }

    //绘制图表
    private void plotEChart(BlockEChart block) throws DocumentException {
        this.document.newPage();
        //添加一个描述信息
        Phrase phrase = new Phrase(block.getDesc()+"\n", font_bold);
        this.document.add(phrase);

        //添加一张图表
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(block.getContents().substring(23));
            for(int k = 0; k < b.length; k++){
                if(b[k] < 0){
                    b[k] += 256;
                }
            }
            Image logo = Image.getInstance(b);
            logo.setAlignment(Image.ALIGN_CENTER);
            logo.scalePercent(41);
            this.document.add(logo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
