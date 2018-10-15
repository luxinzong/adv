package com.suma.pojo;

import java.util.Date;

public class AdvInfo {
    private Long id;

    private Long advLocationId;

    private Long advTypeId;

    private String name;

    private Integer materialType;

    private Integer status;

    private Date startDate;

    private Date endDate;

    private Date periodTimeStart;

    private Date periodTimeEnd;

    private String checkUser;

    private String checkNote;

    private Date checkTime;

    private String createdUser;

    private Date createdTime;

    private Date lastEditTime;

    private String lastEditUser;

    private String lastEditModule;

    private Integer region;

    private String reservedString;

    private Long reservedInt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdvLocationId() {
        return advLocationId;
    }

    public void setAdvLocationId(Long advLocationId) {
        this.advLocationId = advLocationId;
    }

    public Long getAdvTypeId() {
        return advTypeId;
    }

    public void setAdvTypeId(Long advTypeId) {
        this.advTypeId = advTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPeriodTimeStart() {
        return periodTimeStart;
    }

    public void setPeriodTimeStart(Date periodTimeStart) {
        this.periodTimeStart = periodTimeStart;
    }

    public Date getPeriodTimeEnd() {
        return periodTimeEnd;
    }

    public void setPeriodTimeEnd(Date periodTimeEnd) {
        this.periodTimeEnd = periodTimeEnd;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    public String getCheckNote() {
        return checkNote;
    }

    public void setCheckNote(String checkNote) {
        this.checkNote = checkNote == null ? null : checkNote.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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

    public String getLastEditModule() {
        return lastEditModule;
    }

    public void setLastEditModule(String lastEditModule) {
        this.lastEditModule = lastEditModule == null ? null : lastEditModule.trim();
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getReservedString() {
        return reservedString;
    }

    public void setReservedString(String reservedString) {
        this.reservedString = reservedString == null ? null : reservedString.trim();
    }

    public Long getReservedInt() {
        return reservedInt;
    }

    public void setReservedInt(Long reservedInt) {
        this.reservedInt = reservedInt;
    }
}