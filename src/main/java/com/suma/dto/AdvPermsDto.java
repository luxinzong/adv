package com.suma.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/25 0025
 * @Description 为前端设计的用户对应权限列表
 **/
@Data
public class AdvPermsDto {

    private String title = "";

    private String path = "null";

    private String index = "null";

    private List<AdvPermsDto> children = Lists.newArrayList();


}
