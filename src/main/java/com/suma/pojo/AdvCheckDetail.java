package com.suma.pojo;

import java.util.Date;

public class AdvCheckDetail {

    private Long id;//审核详情ID

    private Long advInfoId;//关联广告ID

    private Integer region;//区域码

    private String username;//审核人

    private Integer status;//广告状态

    private String mark;//备注

    private Date times;//审核时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdvInfoId() {
        return advInfoId;
    }

    public void setAdvInfoId(Long advInfoId) {
        this.advInfoId = advInfoId;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }
}