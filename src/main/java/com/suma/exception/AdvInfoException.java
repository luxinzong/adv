package com.suma.exception;

/**
 * @auther: luxinzong
 * @date: 2018/10/17
 * @description 广告信息
 */
public class AdvInfoException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public AdvInfoException() {}

    public AdvInfoException(String errorMsg){
        super(errorMsg);
    }

}
