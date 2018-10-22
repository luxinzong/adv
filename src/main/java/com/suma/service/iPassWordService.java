package com.suma.service;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19
 * @Description 密码服务层
 **/
public interface iPassWordService {

    public String encryptPassword(String username,String password,String salt);

}
