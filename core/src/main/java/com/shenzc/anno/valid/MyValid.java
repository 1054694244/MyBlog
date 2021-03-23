package com.shenzc.anno.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @shenzc
 * @2020/12/14 9:46
 * 文件说明：自定义@Valid属性
 */
@Constraint(validatedBy = {MyConstraintValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyValid {
    String message() default "自定义不能为空";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
