package com.shenzc.anno.valid;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyValid, Object> {

    @Override
    public void initialize(MyValid constraintAnnotation) {
        System.out.println("myValid inti");
    }

    //校检是否为空
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }
        return true;
    }
}