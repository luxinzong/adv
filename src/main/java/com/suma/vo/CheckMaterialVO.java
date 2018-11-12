package com.suma.vo;

import lombok.Data;

import java.util.Date;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/11/08
 * @description:
 */
@Data
public class CheckMaterialVO {
    private Long id;

    private Integer materialType;

    private String fileName;

    private String fileType;

    private String fileSaveName;

    private String filePath;

    private String fileUrl;

    private String href;

    private String md5;

    private String mark;

    /*private String checkUser;

    private Date checkTime;

    private String createdUser;

    private Date createdTime;

    private String lastEditUser;

    private Date lastEditTime;*/

    private Long fileLength;

    private Integer sequence;

    private Integer duration;
}
