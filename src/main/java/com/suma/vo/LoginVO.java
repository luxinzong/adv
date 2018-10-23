package com.suma.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description 登录vo
 **/
@Data
public class LoginVO {

    @NotBlank
    @Length(max = 32)
    private String username;
    @NotBlank
    @Length(max = 32)
    private String password;

    private boolean rememberMe;

}
