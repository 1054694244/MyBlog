package com.shenzc.anno.excel;

import java.lang.annotation.*;

/**
 * @shenzc
 * @2020/12/10 9:01
 * 文件说明：
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelAnno {

    boolean isImport() default true;

    String title() default "";

    int column() default 1;

}
