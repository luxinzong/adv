package com.suma.service.impl;

import com.suma.constants.ExceptionConstants;
import com.suma.exception.LoginException;
import com.suma.pojo.AdvUser;
import com.suma.service.iPassWordService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description
 **/
@Service
public class PassWordService implements iPassWordService {


    @Override
    public void validate(AdvUser advUser, String password) {
        if(!matches(advUser,password)){
            throw new LoginException(ExceptionConstants.LOGIN_EXCEPTION_PASSWORD_NOT_RIGHT);
        }
    }

    @Override
    public boolean matches(AdvUser advUser, String selectPassword) {
        return advUser.getPassword().equals(encryptPassword(advUser.getUserName(),selectPassword,advUser.getSalt()));
    }

    /**
     * 对密码进行加密
     *
     * @param username
     * @param password
     * @param salt
     * @return
     */
    @Override
    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }
}
