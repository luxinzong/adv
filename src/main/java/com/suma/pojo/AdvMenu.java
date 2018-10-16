package com.suma.pojo;

import lombok.Data;
import lombok.ToString;
/**
 * @Autor gaozhongbao
 * @Date 2018/10/16
 * @Description 菜单对象
 **/
@Data
public class AdvMenu extends BasePojo{

    private static final long serialVersionUID = 1L;
    //菜单ID
    private Integer menuId;
    //菜单名称
    private String menuName;
    //父菜单ID
    private Integer parentId;
    //组级ID
    private String ancestors;
    //显示顺序
    private Integer orderNum;
    //菜单URL
    private String url;
    //菜单类型:0目录,1菜单,2按钮
    private String menuType;
    //菜单状态:0显示,1隐藏
    private String visible;
    //权限字符串
    private String perms;

}