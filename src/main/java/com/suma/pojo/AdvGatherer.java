package com.suma.pojo;

public class AdvGatherer {
    private Long id;

    private Integer regionId;

    private String highStandardType;

    private String fileName;

    private String freq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getHighStandardType() {
        return highStandardType;
    }

    public void setHighStandardType(String highStandardType) {
        this.highStandardType = highStandardType == null ? null : highStandardType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq == null ? null : freq.trim();
    }
}