package com.suma.service;
import com.suma.pojo.AdvUser;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description
 **/

public interface iPassWordService {



    public void validate(AdvUser advUser, String password);

    public boolean matches(AdvUser advUser, String selectPassword);

    public String encryptPassword(String username, String password, String salt);



}
