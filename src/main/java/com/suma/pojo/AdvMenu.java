package com.suma.pojo;

import lombok.Data;


@Data
public class AdvMenu extends BasePojo{

    private Integer menuId;

    private String menuName;

    private Integer parentId;

    private Integer orderNum;

    private String url;

    private String menuType;

    private String visible;

    private String perms;

}