package com.suma.exception;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description
 **/
class BaseException extends RuntimeException {
    BaseException() {
        super();
    }

    BaseException(String message) {
        super(message);
    }
}
