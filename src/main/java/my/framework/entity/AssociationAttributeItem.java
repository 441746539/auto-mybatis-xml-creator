package my.framework.entity;

import my.framework.util.StringUtil;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class AssociationAttributeItem extends AbstractAttributeItem {
    /**
     * 级联对象class类型
     */
    public Class itemClass;
    /**
     * 映射的数据库外键值
     */
    public String foreignKeyColunm;

    /**
     * 本字段与关联表中那个数据列名的数据列做外键关联
     */
    public String relColumnInforeignTable;


    /**
     * 关联查询方法名
     */
    public String associateSelectMethod(){
        if(StringUtil.isNotNull(super.attrName))
            return "find_associate_"+super.attrName;
        else
            throw new RuntimeException("生成关联条件查询方法名发生异常！super.attrName IS NULL!");
    }
}
