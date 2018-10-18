package com.suma.exception;

/**
 * @auther: luxinzong
 * @date: 2018/10/17 0017
 * @description 广告信息与广告资源信息维护异常处理
 */
public class InfoMaterialException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InfoMaterialException() {
    }

    public InfoMaterialException(String errorMsg) {
        super(errorMsg);
    }
}
