package likou.threadlocaldemo.core;

import lombok.Data;

/**
 * 图表数据块，描述图表信息
 * Created by byc on 8/15/18.
 */
@Data
public class BlockEChart extends BlockBase {
    private String contents;//图表内存

    public BlockEChart(String desc, String contents) {
        this.type = BlockType.ECHART;
        this.desc = desc;
        this.contents = contents;
    }
}
