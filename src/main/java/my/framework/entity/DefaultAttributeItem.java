package my.framework.entity;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class DefaultAttributeItem extends AbstractAttributeItem {

    /**
     * 主键id
     */
    public boolean isPrimaryId = false;
    /**
     * 对应数据库的列名
     */
    public String columnName;

    /**
     * 是否时间类型
     */
    public boolean isDateType;

    /**
     * 时间类型格式
     */
    public String datePattern;
}

