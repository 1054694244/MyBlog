package com.shenzc.service;

import com.shenzc.entity.backendUser.Category;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @shenzc
 * @2020/12/14 10:51
 * 文件说明：
 */
@Service
public class BeanValidationService {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 返回所有不合格属性信息
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> validationAll(T t){
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> violation : violations){
            messageList.add(violation.getMessage());
        }
        return messageList;
    }

    /**
     * 返回第一个属性不合格的信息
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String validation(T t){
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        for (ConstraintViolation<T> violation : violations){
            return violation.getMessage();
        }
        return null;
    }


    public static void main(String[] args) {
        Category category = new Category();
        List<String> list = validationAll(category);
        System.out.println(list);
    }
}
