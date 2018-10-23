package com.suma.vo;
import lombok.Data;

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
