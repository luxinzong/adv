package com.suma.exception;

/**
 * @author: luxinzong
 * @date: 2018/11/12 0012
 * @description
 */
public class InfoVersionException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public InfoVersionException() {

    }

    public InfoVersionException(String errorCode) {
        super(errorCode);
    }

}
