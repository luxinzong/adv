package com.suma.pojo;

import lombok.Data;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@Data
public class AdvResponseVO {

    private String resultCode;

    private String resultDesc;

    private Long resultCount;

    private String sessionId;

    private Long checkInterval;

    private List<AdvItem> advItem;
}
