package com.suma.controller;

import com.suma.utils.Result;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description Web层通用数据处理
 **/
public class BaseController {

    /**
     * 将前台传递过过来的日期格式字符串，自动转换成Date类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 响应返回结果
     *
     * @param rows
     * @return
     */
    protected Result toResult(int rows){
        return rows > 0 ? Result.success() : Result.error();
    }



}
