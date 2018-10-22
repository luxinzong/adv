package com.suma.service.impl;

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
