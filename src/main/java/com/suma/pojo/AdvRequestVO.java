package com.suma.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@Data
public class AdvRequestVO {

    @NotNull
    private String sessionId;//随机生成对当前请求的唯一标

    @NotNull
    private String clientId;//大屏下为机顶盒ca卡号，其他平台传入用户注册账号id

    private String advType;//广告位类型编码，可以为空或只有一个值

    private String advSubType;//广告位子类型编码，可以为空或多个值，以逗号隔开

    @NotNull
    private Long advID;//广告位id，0 表示不按广告位请求

    private Integer deviceType;//设备信息编码0表示大屏 1表示手机 2表示平板 3表示电脑

    @NotNull
    private Long reqNum;//请求广告数，-1，表示请求所有广告，0 按广告位定义给出

    private String regionCode;//区域码

    private String NetworkID;//网络ID

    private String tsId;//传输流ID

    private String serviceID;//节目ID

    private String freq;//频点

    private String channelID;//频道号

    private Map<String, String> extInfos;


}
