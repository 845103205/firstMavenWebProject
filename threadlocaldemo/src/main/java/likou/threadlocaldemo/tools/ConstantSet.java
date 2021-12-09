package likou.threadlocaldemo.tools;

/**
 * 实时监控历史数据常量集合
 */
public class ConstantSet {

    /**mysql需要返回字段**/
    public static  final  String[] MYSQL_ATTRIBUTES = new String[]{"equipmentInfoGid","categoryIndex","updateTime","equipmentName","name"};

    /**mysql返回属性常量**/
    public static  final  String COMPOSE_VALUE = "resultValue";

    /**设备id外键常量**/
    public static final String EQUIPMENTINFOGID = "equipmentInfoGid";
    /**主键id常量**/
    public static final String GID = "gid";
    /**设备名称常量**/
    public static final String EQUIPMENTNAME = "name";
    /**中文名称常量**/
    public static final String DESCRIBECHS = "describeChs";
    /**单位名称常量**/
    public static final String UNIT = "unit";

    /**变量前缀常量**/
    public static final String VARIABLEPREFIX = "variablePrefix";
    /**变量名称常量**/
    public static final String VARIABLENAME = "variableName";
    /**取值常量**/
    public static final String CONVERTVALUE = "convertValue";
    /**排序属性常量**/
    public static final String ORDERINDEX = "orderIndex";

    /**结果状态值***/
    public static final int RESULTSTATUS_VALUE = 1;

}
