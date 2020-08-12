package com.huiztech.leaf.common.annotation;

import java.lang.annotation.*;

/**
 * @author [muyuanqiang]
 * @version [1.0.0]
 * @date: [2020/8/10 22:58]
 * @description []
 */
@Inherited
@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UuidKeyAnnotation {
    /**
     * key value
     */
    String value() default "";
}
