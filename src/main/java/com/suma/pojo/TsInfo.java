package com.suma.pojo;

import com.suma.utils.Insert;
import com.suma.utils.Update;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TsInfo {
    @NotNull(groups = Update.class)
    private Long id;

    @NotNull(groups = {Insert.class, Update.class})
    private String tsId;

    @NotNull(groups = {Insert.class, Update.class})
    private String tsName;

    @NotNull(groups = {Insert.class})
    private Long nid;

    private String createdUser;

    private Date createdTime;

    private String lastEditUser;

    private Date lastEditTime;

    private Integer region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId == null ? null : tsId.trim();
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName == null ? null : tsName.trim();
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
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

    public String getLastEditUser() {
        return lastEditUser;
    }

    public void setLastEditUser(String lastEditUser) {
        this.lastEditUser = lastEditUser == null ? null : lastEditUser.trim();
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }
}