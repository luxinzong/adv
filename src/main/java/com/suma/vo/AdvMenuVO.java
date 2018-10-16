package com.suma.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description
 **/
@Data
public class AdvMenuVO {

    @NotBlank
    private String menuName;
    //父菜单id
    private Integer parentId;
    //显示顺序
    private Integer orderNum;
    //菜单url
    private String url;
    //菜单类型：0目录,1菜单，2按钮
    private String menuType;
    //权限字符串
    private String perms;

}
