package likou.threadlocaldemo.tools;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Range;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 重写获取时间维度，添加进步时长
 * @Author: wx
 * @Date: 2020/6/23
 */
public class DateRangeUtile extends Range<DateTime> {
    private static final long serialVersionUID = -8032551471152568716L;

    public DateRangeUtile(Date start, Date end, DateField unit) {
        this(start, end, unit, 1);
    }

    public DateRangeUtile(Date start, Date end, DateField unit, int step) {
        this(start, end, unit, step, true, true);
    }

    public DateRangeUtile(Date start, Date end, DateField unit, int step, boolean isIncludeStart, boolean isIncludeEnd) {
        super(DateUtil.date(start), DateUtil.date(end), (current, end1, index) -> {
            DateTime dt = current.offsetNew(unit, step);
            if (dt.isAfter(end1)) {
                return null;
            }
            return current.offsetNew(unit, step);
        }, isIncludeStart, isIncludeEnd);
    }

    /**
     * 单个进步
     * @param start 开始时间
     * @param end 结束时间
     * @param unit 进步单位
     * @return
     */
    public static DateRangeUtile range(Date start, Date end, DateField unit) {
        return new DateRangeUtile(start, end, unit);
    }


    /**
     * 单个进步
     * @param start 开始时间
     * @param end 结束时间
     * @param unit 进步单位
     * @return
     */
    public static List<DateTime> rangeToList(Date start, Date end, DateField unit) {
        return CollUtil.newArrayList((Iterator<DateTime>) range(start, end, unit));
    }

    /**
     * 单个进步
     * @param start 开始时间
     * @param end 结束时间
     * @param unit 进步单位
     * @param step 时间刻度
     * @return
     */
    public static DateRangeUtile range(Date start, Date end, DateField unit,int step) {
        return new DateRangeUtile(start, end, unit,step);
    }


    /**
     * 单个进步
     * @param start 开始时间
     * @param end 结束时间
     * @param unit 进步单位
     * @param step 时间刻度
     * @return
     */
    public static List<DateTime> rangeToList(Date start, Date end, DateField unit,int step) {
        return CollUtil.newArrayList((Iterator<DateTime>) range(start, end, unit,step));
    }

}
