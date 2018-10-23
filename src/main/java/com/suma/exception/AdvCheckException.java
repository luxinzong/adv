package com.suma.exception;

/**
 * @auther: luxinzong
 * @date: 2018/10/23 0023
 * @description
 */
public class AdvCheckException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public AdvCheckException() {
    }

    public AdvCheckException(String errorMsg) {
        super(errorMsg);
    }

}
