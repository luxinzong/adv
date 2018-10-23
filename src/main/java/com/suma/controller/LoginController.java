package com.suma.controller;

import com.suma.constants.CommonConstants;
import com.suma.pojo.AdvUser;
import com.suma.utils.Result;
import com.suma.utils.ShiroUtils;
import com.suma.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description 登录controller
 **/
@RestController
@Slf4j
public class LoginController extends BaseController{

    @GetMapping("/index")
    public Result index(){
        return Result.loginSuccess();
    }

    @GetMapping("/unauth")
    public Result unauth(){
        return Result.error(CommonConstants.NO_PERMS);
    }


    @GetMapping("/login")
    public Result login(){
        AdvUser advUser = ShiroUtils.getUser();
        if(advUser != null){
            return index();
        }
        return Result.error(CommonConstants.NO_LOGIN);
    }

    @PostMapping("/login")
    public Result login(@Validated LoginVO loginVO){
        AdvUser advUser = ShiroUtils.getUser();
        if(advUser != null){
            return index();
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUsername(), loginVO.getPassword(), loginVO.isRememberMe());
        subject.login(token);
        return Result.loginSuccess();

    }

    @GetMapping("/logout")
    public Result logout(){
        Subject subject = ShiroUtils.getSubject();
        subject.logout();

        return Result.loginOut();
    }


}






