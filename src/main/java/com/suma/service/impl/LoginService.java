package com.suma.service.impl;

import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvUserMapper;
import com.suma.exception.LoginException;
import com.suma.pojo.AdvUser;
import com.suma.service.iLoginService;
import com.suma.service.iPassWordService;
import com.suma.utils.ShiroUtils;
import com.suma.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suma.service.iAdvUserService;

import java.util.Date;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description 登录服务
 **/
@Service
public class LoginService implements iLoginService {

    @Autowired
    private iPassWordService passwordService;
    @Autowired
    private iAdvUserService userService;
    @Autowired
    private AdvUserMapper advUserMapper;


    @Override
    public AdvUser login(LoginVO loginVO) {
        String username = loginVO.getUsername();
        AdvUser advUser = userService.selectByUsername(username);
        if(advUser == null){
            throw new LoginException(ExceptionConstants.LOGIN_EXCEPTION_USERNAME_NOT_EXIST);
        }

        String password = loginVO.getPassword();
        passwordService.validate(advUser,password);
        recordLoginInfo(advUser);

        return advUser;
    }

    @Override
    public void recordLoginInfo(AdvUser user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(new Date());
        advUserMapper.updateByPrimaryKeySelective(user);
    }
}
