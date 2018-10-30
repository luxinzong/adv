package com.suma.controller;

import com.suma.constants.CommonConstants;
import com.suma.pojo.AdvUser;
import com.suma.utils.Result;
import com.suma.utils.ShiroUtils;
import com.suma.vo.LoginVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description 登录controller
 **/
@CrossOrigin
@RestController
public class LoginController extends BaseController{

    @GetMapping("/index")
    public Result index(){
        AdvUser advUser = ShiroUtils.getUser();
        if(advUser != null){
            return Result.loginSuccess();
        }

        return Result.error(CommonConstants.NO_LOGIN);
    }

    @GetMapping("/unauth")
    public Result unauth(){
        return Result.error(CommonConstants.NO_PERMS);
    }


    @GetMapping("/login")
    public Result login(){
        AdvUser advUser = ShiroUtils.getUser();
        if(advUser != null){
            return Result.hasLogined();
        }
        return Result.error(CommonConstants.NO_LOGIN);
    }

    @PostMapping("/login")
    public Result login(@Validated LoginVO loginVO){
        AdvUser advUser = ShiroUtils.getUser();
        Subject subject = SecurityUtils.getSubject();
        if(advUser != null){
            return Result.hasLogined();
        }
        UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUsername(), loginVO.getPassword());
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






