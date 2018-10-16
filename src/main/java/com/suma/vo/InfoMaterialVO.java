package com.suma.vo;

/**
 * @auther: luxinzong
 * @date: 2018/10/16 0016
 * @description
 */
public class InfoMaterialVO {

    private Long advInfoId;

    private Long materialId;

    private String fileName;

    private Integer duration;

    private Integer sequence;

    public Long getAdvInfoId() {
        return advInfoId;
    }

    public void setAdvInfoId(Long advInfoId) {
        this.advInfoId = advInfoId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
