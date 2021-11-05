package likou.threadlocaldemo.core;

import lombok.Data;

import java.util.List;

/**
 * 表格数据块，描述表格信息
 * Created by byc on 8/15/18.
 */
@Data
public class BlockTable extends BlockBase {
    //表格头信息
    private List<String> titleCols;
    //表格行数据，每行是一个个列的内容
    private List<List<String>> rows;

    public BlockTable(String desc, List<String> titleCols, List<List<String>> rows) {
        this.type = BlockType.TABLE;
        this.desc = desc;
        this.titleCols = titleCols;
        this.rows = rows;
    }
}
