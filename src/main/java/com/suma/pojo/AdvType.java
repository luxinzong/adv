package com.suma.pojo;

public class AdvType {
    private Long id;

    private String advtype;

    private String advtypename;

    private String advsubtype;

    private String advsubtypename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvtype() {
        return advtype;
    }

    public void setAdvtype(String advtype) {
        this.advtype = advtype == null ? null : advtype.trim();
    }

    public String getAdvtypename() {
        return advtypename;
    }

    public void setAdvtypename(String advtypename) {
        this.advtypename = advtypename == null ? null : advtypename.trim();
    }

    public String getAdvsubtype() {
        return advsubtype;
    }

    public void setAdvsubtype(String advsubtype) {
        this.advsubtype = advsubtype == null ? null : advsubtype.trim();
    }

    public String getAdvsubtypename() {
        return advsubtypename;
    }

    public void setAdvsubtypename(String advsubtypename) {
        this.advsubtypename = advsubtypename == null ? null : advsubtypename.trim();
    }
}