package com.suma.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/11 0011
 * @Description 基类，主要包含通用参数
 **/
@Data
public class BasePojo implements Serializable {

    private static final long serialVersionUID = 1L;

    //创建者
    private String createBy;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //更新者
    private String updateBy;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    //备注
    private String remark;

}
