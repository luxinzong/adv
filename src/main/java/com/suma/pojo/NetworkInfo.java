package com.suma.pojo;

import java.util.Date;

public class NetworkInfo {
    private Long networkId;

    private Long orNetworkId;

    private String networkName;

    private String createdUser;

    private Date createdTime;

    private Date lastEditTime;

    private String lastEditUser;

    private Integer region;

    public Long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }

    public Long getOrNetworkId() {
        return orNetworkId;
    }

    public void setOrNetworkId(Long orNetworkId) {
        this.orNetworkId = orNetworkId;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName == null ? null : networkName.trim();
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser == null ? null : createdUser.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getLastEditUser() {
        return lastEditUser;
    }

    public void setLastEditUser(String lastEditUser) {
        this.lastEditUser = lastEditUser == null ? null : lastEditUser.trim();
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }
}