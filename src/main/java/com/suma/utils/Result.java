package com.suma.utils;

import com.suma.constants.CommonConstants;
import lombok.Data;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/11 0011
 * @Description 前后台信息沟通体
 **/
@Data
public class Result {

    private static final long serialVersionUID = 1L;

    private int resultCode;

    private String resultDesc;

    private Object resultData;




    public static Result buildResult(int resultCode,String resultDesc,Object resultData){
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setResultDesc(resultDesc);
        result.setResultData(resultData);
        return result;
    }

  /*  public Result() {
    }

    public Result(int total,int resultCode,String resultDesc,Object resultData) {
        this.total = total;
        this.resultCode = resultCode;
        this.resultData = resultData;
        this.resultDesc = resultDesc;

    }*/

    /**
     * 返回成功信息
     *
     * @return
     */
    public static Result success(){
        return buildResult(CommonConstants.SUCCESS,"操作成功",null);
    }

    public static Result success(Object resultData){
        return buildResult(CommonConstants.SUCCESS,"操作成功",resultData);
    }


    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Result error(){
        return buildResult(CommonConstants.FAIL,"操作失败",null);
    }

    public static Result selectIsNullError(){return buildResult(CommonConstants.FAIL,"查询数据为空",null);}

    public static Result sysytemError(){
        return buildResult(CommonConstants.SYSTEM_ERROR,"系统错误",null);
    }

    /**
     * 自定义错误消息
     *
     * @param msg
     * @return
     */
    public static Result error(String msg){
        return buildResult(CommonConstants.FAIL,msg,null);
    }

}
