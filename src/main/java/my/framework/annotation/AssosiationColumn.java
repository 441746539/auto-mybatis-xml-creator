package my.framework.annotation;

import java.lang.annotation.*;

/**
 * 标识实体类字段是一对一 多对一关系映射
 * Created by qiang.su on 2017/7/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AssosiationColumn {
    /**
     * 数据表中外键字段 ,是数据库列名
     * @return
     */
    public String foreignKeyColunm();

    /**
     * 本表的外键字段 对应了另一张关联表中的那个数据列
     * @return
     */
    public String relColumnInforeignTable();
}
