package com.suma.vo;

import lombok.Data;

@Data
public class ServiceQueryVO {

    private Long id;
    //网络ID
    private String networkId;
    //网络名称
    private String networkName;
    //传输流ID
    private String tsId;
    //传输流名称
    private String tsName;
    //业务ID
    private String serviceId;
    //业务名称
    private String serviceName;

}
