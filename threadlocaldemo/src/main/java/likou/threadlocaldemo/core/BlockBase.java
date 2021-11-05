package likou.threadlocaldemo.core;

import lombok.Data;

/**
 * 报表模块基类
 * 报表数据按块描述，一个数据块就表示一种数据类型：表格，图表等。
 * Created by byc on 8/15/18.
 */
@Data
public class BlockBase {
    protected BlockType type;//报表块类型
    protected String desc;//描述文本

    public enum BlockType {
        TABLE,//表格类型
        ECHART//图表类型
    }
}
