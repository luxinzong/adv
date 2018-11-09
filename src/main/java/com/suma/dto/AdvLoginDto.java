package com.suma.dto;

import lombok.Data;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/24 0024
 * @Description
 **/
@Data
public class AdvLoginDto {

    private String username;

    private String token;

    private List<AdvPermsDto> advPermsDtoList;
}
