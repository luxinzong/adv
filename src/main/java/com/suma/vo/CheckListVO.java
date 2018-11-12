package com.suma.vo;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvLocation;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/11/08
 * @description: 审核列表
 */
@Data
public class CheckListVO {

    private Long advInfoId;//广告ID

    private String advName;//广告名称

    private String advtypename;//广告类型名称

    private String advsubtypename;//广告子类型名称

    private Integer status;//广告状态

    private Date lastEditTime;//最后修改时间

    /*private String checkUser;//审核人

    private String checkNote;//审核意见

    private Date checkTime;//审核时间*/

    //private Integer region;//区域码
}
