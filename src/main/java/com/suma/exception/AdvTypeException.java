package com.suma.exception;

/**
 * @author: luxinzong
 * @date: 2018/11/12 0012
 * @description
 */
public class AdvTypeException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    public AdvTypeException() {

    }
    public AdvTypeException(String errorCode) {
        super(errorCode);
    }
}
