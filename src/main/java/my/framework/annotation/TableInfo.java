package my.framework.annotation;

import java.lang.annotation.*;

/**
 * 标识mybatis映射实体类的时候需要的参数信息
 * Created by qiang.su on 2017/7/24.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableInfo {
    /**
     * 实体类映射的数据库表名
     * @return
     */
    public String tableName();

    /**
     * mapper.xml中，所有的入参的parameterType 一般是vo dto等包含页面参数的实体类扩展类
     * @return
     */
    public String parameterType();

    /**
     * 给表对应resultMap起名  一般是“实体类名”+“Map”
     * @return
     */
    public String resultMap();
}
