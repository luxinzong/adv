package com.suma.service.impl;

import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvUserMapper;
import com.suma.dto.AdvLoginDto;
import com.suma.dto.AdvPermsDto;
import com.suma.exception.LoginException;
import com.suma.pojo.AdvUser;
import com.suma.service.iAdvMenuService;
import com.suma.service.iLoginService;
import com.suma.service.iPassWordService;
import com.suma.utils.ShiroUtils;
import com.suma.vo.LoginVO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.suma.service.iAdvUserService;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private iAdvMenuService advMenuService;
    @Autowired
    private SessionDAO sessionDAO;


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

    @Override
    public AdvLoginDto produceLoginDto() {
        AdvLoginDto advLoginDto = new AdvLoginDto();
        advLoginDto.setUsername(ShiroUtils.getUser().getUserName());
        advLoginDto.setToken(String.valueOf(ShiroUtils.getSession().getId()));
        List<AdvPermsDto> advPermsDtoList = advMenuService.selectMenuTreeByUserName(ShiroUtils.getUser().getUserName());
        advLoginDto.setAdvPermsDtoList(advPermsDtoList);

        return advLoginDto;
    }

    @Override
    public void logout() {
        Subject subject = ShiroUtils.getSubject();
        //清除session信息
        subject.getSession().stop();
        //退出登录
        subject.logout();
    }
}
