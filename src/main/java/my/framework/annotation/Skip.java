package my.framework.annotation;

import java.lang.annotation.*;

/**
 * 标识该字段不参与解析
 * Created by qiang.su on 2017/7/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Skip {
}
