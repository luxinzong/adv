package com.suma.controller;

import com.google.common.base.Strings;
import com.suma.constants.CommonConstants;
import com.suma.dto.AdvLoginDto;
import com.suma.pojo.AdvUser;
import com.suma.service.iLoginService;
import com.suma.utils.Result;
import com.suma.utils.ShiroUtils;
import com.suma.vo.LoginVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description 登录controller
 **/
@CrossOrigin
@RestController
public class LoginController extends BaseController{


    @Autowired
    private iLoginService loginService;

    @GetMapping("/index")
    public Result index(){
        AdvUser advUser = ShiroUtils.getUser();
        if(advUser != null){
            AdvLoginDto advLoginDto = loginService.produceLoginDto();
            return Result.loginSuccess(advLoginDto);
        }

        return Result.error(CommonConstants.NO_LOGIN);
    }

    @GetMapping("/unauth")
    public Result unauth(){
        return Result.error(CommonConstants.NO_PERMS);
    }

    @GetMapping("/login")
    public Result getLogin(){
        AdvUser advUser = ShiroUtils.getUser();
        if(advUser != null){
           AdvLoginDto advLoginDto = loginService.produceLoginDto();
            return Result.hasLogined(advLoginDto);
        }else{
            return Result.error("请登录");
        }
    }

    @PostMapping("/login")
    public Result login(@Valid LoginVO loginVO){
        AdvUser advUser = ShiroUtils.getUser();
        AdvLoginDto advLoginDto;
        if(advUser != null){
            advLoginDto = loginService.produceLoginDto();
            return Result.hasLogined(advLoginDto);
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginVO.getUsername(), loginVO.getPassword());
            subject.login(token);
            //按前端要求，对登录结果添加对应权限信息
            advLoginDto = loginService.produceLoginDto();
            return Result.loginSuccess(advLoginDto);
        }
    }



    @GetMapping("/logout")
    public Result logout(){
        loginService.logout();

        return Result.loginOut();
    }


}







