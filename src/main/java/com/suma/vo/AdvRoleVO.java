package com.suma.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/18 0018
 * @Description  角色信息VO
 **/
@Data
public class AdvRoleVO {

    @NotBlank
    @Length(max = 32)
    private String roleName;
    @NotBlank
    @Length(max = 128)
    private String roleKey;
    //角色顺序
    private Integer roleSort;
    //角色状态
    private String status;
    //角色对应id信息
    @NotEmpty
    private List<Integer> menuIds;
    @NotBlank
    private String remark;
}

