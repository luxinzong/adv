package com.suma.exception;

import org.apache.shiro.authz.AuthorizationException;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description
 **/
public class LoginException extends RuntimeException {

    public LoginException(){};

    public LoginException(String errorMsg){
        super(errorMsg);
    }


}
