package com.shenzc.exception;

import com.shenzc.resutl.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class BadRequestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);
    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBody validationBodyException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult();
      /**
      *可解开
      **/
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            /*errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                logger.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
            });*/
            FieldError fieldError = (FieldError)errors.get(0);
            String msg = "Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                    "},errorMessage{"+fieldError.getDefaultMessage()+"}";
            return ResultBody.fail500(msg);
        }
        return ResultBody.fail500("错误");
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResultBody parameterTypeException(HttpMessageConversionException exception){
        logger.error(exception.getCause().getLocalizedMessage());
        return ResultBody.fail500("类型转换错误");
    }
}