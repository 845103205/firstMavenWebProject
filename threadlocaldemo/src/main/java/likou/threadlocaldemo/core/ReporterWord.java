package likou.threadlocaldemo.core;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.GrayColor;
import com.lowagie.text.rtf.RtfWriter2;
import likou.threadlocaldemo.Logger.Logger;
import likou.threadlocaldemo.common.Helpers;
import sun.misc.BASE64Decoder;

import java.awt.*;
import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * 制作WORD格式报表
 * Created by byc on 8/17/18.
 */
public class ReporterWord extends Reporter {
    private Document document;

    private static String fontPath = "/usr/share/fonts/default/simsun.ttc,1";
    //标题颜色
    private final Color color_title = new Color(51, 122, 183);
    //行颜色
    private final Color color_row = new Color(249, 249, 249);

    private Font font_normal;
    private Font font_bold;

    public ReporterWord() {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            Logger.error("ReporterPdf", e);
        }

        font_normal = new Font(baseFont);
        font_bold = new Font(baseFont, Font.UNDEFINED, Font.BOLD);
    }

    @Override
    protected void execute() throws Exception {
        Rectangle rectangle = new Rectangle(PageSize.A4.rotate());
        rectangle.setBorderColor(Color.BLACK);
        this.document = new Document(rectangle);
        RtfWriter2 writer = RtfWriter2.getInstance(this.document, this.out);
        writer.setCloseStream(false);
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
    }

    //绘制封面
    void addCover() throws Exception {
        Image logo = Image.getInstance(this.getLogo());
        logo.setAlignment(Image.ALIGN_CENTER);
        this.document.add(logo);

        Table table = new Table(2);
        BiConsumer<String, String> f = (caption, content) -> {
            Cell cell = new Cell(caption);
            cell.setBackgroundColor(color_row);
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            table.addCell(cell);

            cell = new Cell(content);
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            table.addCell(cell);
        };

        f.accept(Reporter.captionTitle, this.getTitle());
        f.accept(Reporter.captionUserName, this.getUserName());
        f.accept(Reporter.captionCreateTime, Helpers.dateNowFormat());
        f.accept(Reporter.captionCondition, this.getCondition());

        document.add(table);
    }

    private Cell newCell(String content, Font font) throws BadElementException {
        Cell cell = new Cell(new Phrase(content, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setBorderWidth(1);
        return cell;
    }

    //绘制表格
    private void plotTable(BlockTable block) throws DocumentException {
        this.document.newPage();
        //添加一个描述信息
        Phrase phrase = new Phrase(block.getDesc(), font_bold);
        this.document.add(phrase);

        //添加一个表格
        int cols = block.getTitleCols().size();
        Table table = new Table(cols);
        // 设置表格宽度与对齐
        table.setWidth(100);
        table.setAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));

        // 表头
        for (String headerRow : block.getTitleCols()) {
            Cell head = newCell(headerRow, font_bold);
            head.setBackgroundColor(color_title);
            table.addCell(head);
        }
//        table.setLastHeaderRow(2);
        //设置分页的时候，把表头继续显示上去
        table.setLastHeaderRow(0);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        //表格的数据项
        if (block.getRows().size() > 0) {
            int index = 0;

            for (java.util.List<String> row : block.getRows()) {
                for (String r : row) {
                    Cell cell = newCell(r, font_normal);
                    //表格的行间隔突出显示
                    if (index % 2 == 0) {
                        cell.setBackgroundColor(Color.WHITE);
                    } else {
                        cell.setBackgroundColor(color_row);
                    }
                    table.addCell(cell);
                }

                index++;
            }
        }
        else {
            Cell cell = newCell("无数据", font_normal);
            cell.setBackgroundColor(Color.WHITE);
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
