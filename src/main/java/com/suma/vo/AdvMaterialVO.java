package com.suma.vo;

import lombok.Data;

import java.util.Date;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/16
 * @description:
 */
@Data
public class AdvMaterialVO {
    private Long id;

    private String fileName;

    private String fileType;

    private String fileUrl;

    private String href;

    private String md5;

    private String mark;

    private Integer region;

    private Integer materialType;

    private String typeIds;
}
