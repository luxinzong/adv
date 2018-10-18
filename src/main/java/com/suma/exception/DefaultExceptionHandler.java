package com.suma.exception;


import com.suma.utils.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 自定义异常处理
 **/
@RestControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(DeptException.class)
    public Result deptException(DeptException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }


    @ExceptionHandler(MenuException.class)
    public Result menuException(MenuException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AdvInfoException.class)
    public Result advInfoException(AdvInfoException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(InfoMaterialException.class)
    public Result infoMaterialException(InfoMaterialException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getParameterName() + "不能为空");
    }

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException bindException) {
        log.error(bindException.getMessage(), bindException);
        List<FieldError> fieldErrors = bindException.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        fieldErrors.forEach(fieldError -> {
            stringBuilder.append(fieldError.getField() + fieldError.getDefaultMessage())
                    .append(",");
        });
        return Result.error(stringBuilder.toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error(exception.getMessage(), exception);
        return Result.error(exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        fieldErrors.forEach(fieldError -> {
            stringBuilder.append(fieldError.getField() + fieldError.getDefaultMessage())
                    .append(",");
        });
        return Result.error(stringBuilder.toString());
    }
    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

}