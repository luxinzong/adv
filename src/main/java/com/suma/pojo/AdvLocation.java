package com.suma.pojo;

public class AdvLocation {

    private Long id;

    private String name;

    private Long advTypeId;

    private Long xPosition;

    private Long yPosition;

    private Long mWidth;

    private Long mHeight;

    private String mask;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getAdvTypeId() {
        return advTypeId;
    }

    public void setAdvTypeId(Long advTypeId) {
        this.advTypeId = advTypeId;
    }

    public Long getxPosition() {
        return xPosition;
    }

    public void setxPosition(Long xPosition) {
        this.xPosition = xPosition;
    }

    public Long getyPosition() {
        return yPosition;
    }

    public void setyPosition(Long yPosition) {
        this.yPosition = yPosition;
    }

    public Long getmWidth() {
        return mWidth;
    }

    public void setmWidth(Long mWidth) {
        this.mWidth = mWidth;
    }

    public Long getmHeight() {
        return mHeight;
    }

    public void setmHeight(Long mHeight) {
        this.mHeight = mHeight;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }
}