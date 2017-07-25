package my.framework.annotation;

import java.lang.annotation.*;

/**
 * 标识字段与数据库列之间的关系
 * Created by qiang.su on 2017/7/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBColumn {
    /**
     * 字段对应的数据库的列名称，默认该字段本身名称
     * @return
     */
    public String value() default "";
}
