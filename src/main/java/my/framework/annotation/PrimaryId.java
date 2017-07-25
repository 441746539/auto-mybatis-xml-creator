package my.framework.annotation;

import java.lang.annotation.*;

/**
 * 标识字段映射的是主键
 * Created by qiang.su on 2017/7/24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryId {
}
