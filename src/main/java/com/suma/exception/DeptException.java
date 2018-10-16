package com.suma.exception;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 部门错误异常处理
 **/
public class DeptException extends RuntimeException{

    private  static final long serialVersionUID = 1L;

    public DeptException(){};

    public DeptException(String errorMsg){
        super(errorMsg);
    }

}
