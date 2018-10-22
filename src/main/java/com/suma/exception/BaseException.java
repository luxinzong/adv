package com.suma.exception;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description
 **/
public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
