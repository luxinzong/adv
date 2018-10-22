package com.suma.exception;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description
 **/
public class UserException extends RuntimeException {

    public UserException(){};

    public UserException(String msg){
        super(msg);
    }

}
