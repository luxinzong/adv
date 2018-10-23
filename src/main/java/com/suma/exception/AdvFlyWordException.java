package com.suma.exception;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description
 */
public class AdvFlyWordException extends RuntimeException {
    private static final Long serialVersionUID = 1l;

    public AdvFlyWordException() {
    }

    public AdvFlyWordException(String errorStr) {
        super(errorStr);
    }

}
