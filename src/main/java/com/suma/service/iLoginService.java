package com.suma.service;

import com.suma.dto.AdvLoginDto;
import com.suma.pojo.AdvUser;
import com.suma.vo.LoginVO;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description 用户登录接口
 **/
public interface iLoginService {

    public AdvUser login(LoginVO loginVO);

    public void recordLoginInfo(AdvUser user);

    public AdvLoginDto produceLoginDto();

    public void logout();


}
