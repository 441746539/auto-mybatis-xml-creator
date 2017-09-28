package my.framework.annotation;

import java.lang.annotation.*;

/**
 * 标识字段是时间类型数据
 * Created by qiang.su on 2017/7/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateType {
    /**
     * 时间格式
     * @return
     */
    public String pattern() default "";
}
