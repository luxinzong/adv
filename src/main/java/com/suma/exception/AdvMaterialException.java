package com.suma.exception;

/**
 * @auther: luxinzong
 * @date: 2018/10/23 0023
 * @description
 */
public class AdvMaterialException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public AdvMaterialException() {
    }

    public AdvMaterialException(String errorMsg) {
        super(errorMsg);
    }
}
