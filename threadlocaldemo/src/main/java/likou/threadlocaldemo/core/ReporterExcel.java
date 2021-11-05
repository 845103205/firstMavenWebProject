package likou.threadlocaldemo.core;

import likou.threadlocaldemo.common.Helpers;
import org.apache.poi.hssf.usermodel.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 制作EXCEL格式报表
 * Created by byc on 8/17/18.
 */
public class ReporterExcel extends Reporter {
    HSSFWorkbook workbook;
    HSSFSheet sheet;
    int rowIndex = 0;

    //单元格风格
    HSSFCellStyle styleBold;//字体加粗
    HSSFCellStyle styleBorder;//边框
    HSSFCellStyle styleBorderBold;//边框，字体加粗

    public ReporterExcel() {
        this.workbook = new HSSFWorkbook();
        this.sheet = this.workbook.createSheet();
        this.sheet.setDefaultColumnWidth(20);// 20个字符
        //设置行高，设置了行高自动换行就不能自适应高度
//        this.sheet.setDefaultRowHeight((short) (20 * 20));// 16个点

        //设置字体加粗
        HSSFFont font = this.workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        //单元格风格
        this.styleBold = this.workbook.createCellStyle();
        this.styleBold.setFont(font);

        this.styleBorder = this.workbook.createCellStyle();
        this.styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置自动换行
        this.styleBorder.setWrapText(true);

        this.styleBorderBold = this.workbook.createCellStyle();
        this.styleBorderBold.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.styleBorderBold.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.styleBorderBold.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.styleBorderBold.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //上下居中
        this.styleBorderBold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        this.styleBorderBold.setFont(font);
    }

    @Override
    protected void execute() throws Exception {
        //1 添加封面
        this.addCover();

        //2 添加报表内容
        //按照提供的对象进行图表和表格的绘制
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

        //把报表写入流中，并关闭。
        this.workbook.write(this.out);
        this.workbook.close();
    }

    //绘制封面
    void addCover() throws Exception {
        //添加logo文件
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short)0, this.rowIndex, (short)0, this.rowIndex+1);
        try (ByteArrayOutputStream bs = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(new File(this.getLogo()));
            ImageIO.write(bufferedImage, "png", bs);
            this.sheet.createDrawingPatriarch().createPicture(anchor, workbook.addPicture(bs.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        }
        this.rowIndex += 2;//

        BiConsumer<String, String> f = (caption, content) -> {
            HSSFRow row = this.sheet.createRow(this.rowIndex);
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(this.styleBorderBold);
            cell.setCellValue(caption);

            cell = row.createCell(1);
            cell.setCellStyle(this.styleBorder);
            cell.setCellValue(content);
            this.rowIndex++;
        };

        //绘制报表头
        f.accept(Reporter.captionTitle, this.getTitle());
        f.accept(Reporter.captionUserName, this.getUserName());
        f.accept(Reporter.captionCreateTime, Helpers.dateNowFormat());
        f.accept(Reporter.captionCondition, this.getCondition());
        this.rowIndex += 2;
    }

    //绘制表格
    private void plotTable(BlockTable block) {
        //添加一个描述信息
        HSSFRow row = this.sheet.createRow(this.rowIndex++);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(this.styleBold);
        cell.setCellValue(block.getDesc());



        //添加一个表格
        //表头
        row = this.sheet.createRow(this.rowIndex++);
        int colIndex = 0;
        for (String colName : block.getTitleCols()) {
            cell = row.createCell(colIndex++);
            cell.setCellStyle(this.styleBorderBold);
            cell.setCellValue(colName);
        }

        //表格的数据项
        if (block.getRows().size() > 0) {
            for (List<String> rows : block.getRows()) {
                colIndex = 0;
                row = this.sheet.createRow(this.rowIndex++);

                for (String col : rows) {
                    cell = row.createCell(colIndex++);
                    cell.setCellStyle(this.styleBorder);
                    cell.setCellValue(col.length() > 3000 ? "[超长数据]" + col.substring(0, 3000) : col);
                }
            }
        }
        else {
            row = this.sheet.createRow(this.rowIndex++);
            cell = row.createCell(0);
            cell.setCellStyle(this.styleBold);
            cell.setCellValue("无数据");
        }

        this.rowIndex++;
    }

    //绘制图表
    private void plotEChart(BlockEChart block) {
        //添加一个描述信息
        HSSFRow row = this.sheet.createRow(this.rowIndex++);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(this.styleBold);
        cell.setCellValue(block.getDesc());

        //添加一张图表
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short)0, this.rowIndex, (short)6, this.rowIndex+20);
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(block.getContents().substring(23));
            for (int k = 0; k < b.length; k++) {
                if (b[k] < 0) {
                    b[k] += 256;
                }
            }
            this.sheet.createDrawingPatriarch().createPicture(anchor, workbook.addPicture(b, HSSFWorkbook.PICTURE_TYPE_JPEG));
        }catch (Exception e){
            e.printStackTrace();
        }
        this.rowIndex+=23;
    }
}
