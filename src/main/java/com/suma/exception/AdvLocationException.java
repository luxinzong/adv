package com.suma.exception;

/**
 * @auther: luxinzong
 * @date: 2018/11/1 0001
 * @description
 */
public class AdvLocationException extends BaseException {
    private static final Long serialVersionUID = 1L;

    public AdvLocationException() {}

    public AdvLocationException(String errorMsg){
        super(errorMsg);
    }
}
