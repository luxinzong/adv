package com.suma.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@Data
public class AdvResponseVO {

    /**
     * 返回结果码
     */
    private String resultCode;

    /**
     * 返回结果描述
     */
    private String resultDesc;

    /**
     * 返回广告数
     */
    private Long resultCount;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * 检查时间间隔
     */
    private Long checkInterval;

    /**
     * 广告实体内容
     */
    private List<AdvItem> advItem;


    /**
     * 广告版本号
     */
    private Integer version;
}
