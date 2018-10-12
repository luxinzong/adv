package com.suma.utils;

import lombok.Data;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/11 0011
 * @Description
 **/

public class Result {

    private int resultCode;

    private String resultDesc;

    private Object resultData;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
