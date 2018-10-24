package com.suma.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16
 * @Description 菜单对象
 **/
@Data
public class AdvMenu extends BasePojo{

    private static final long serialVersionUID = 1L;
    //菜单ID
    @NotNull
    private Integer menuId;
    //菜单名称
    @NotBlank
    private String menuName;
    //父菜单ID
    @NotNull
    private Integer parentId;
    //组级ID
    private String ancestors;
    //显示顺序
    private Integer orderNum;
    //菜单URL
    private String url;
    @NotBlank
    //菜单类型:0目录,1菜单,2按钮
    private String menuType;
    //菜单状态:0显示,1禁用
    @NotBlank
    private String status;
    //权限字符串
    private String perms;


}