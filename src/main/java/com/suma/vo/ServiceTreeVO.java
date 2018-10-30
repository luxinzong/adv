package com.suma.vo;

import lombok.Data;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/26
 * @description:
 */
@Data
public class ServiceTreeVO {
    private final String icon = "el-icon-lx-calendar";
    private String value;
    private String type;
    private Long id;
    private String label;
    private List<ServiceTreeVO> children;
}
