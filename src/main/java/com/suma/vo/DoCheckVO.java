package com.suma.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/11/08
 * @description:
 */
@Data
public class DoCheckVO {

    @NotNull
    private Long advId;//广告ID

    private String checkNote;//审核意见

    @NotNull
    private Integer status;//审核状态
}
