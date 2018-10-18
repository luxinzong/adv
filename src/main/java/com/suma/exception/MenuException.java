package com.suma.exception;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description Menu异常处理
 **/
public class MenuException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MenuException(){}

    public MenuException(String errorMsg){
        super(errorMsg);
    }

}
