package com.suma.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/17
 * @description: 频道查询
 */
@Data
public class ServiceQueryVO {

    private Long id;
    //网络ID
    private Long networkId;
    //网络名称
    private String networkName;
    //传输流ID
    private Long tsId;
    //传输流名称
    private String tsName;
    //业务ID
    private Long serviceId;
    //业务名称
    private String serviceName;

}
