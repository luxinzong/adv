package com.suma.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.security.krb5.internal.PAData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/17 0017
 * @Description 统一请求日志处理
 **/
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    //请求之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获得请求url
        String url = request.getRequestURI();
        //获取参数
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr = null;
        while((inputStr = streamReader.readLine()) != null){
            responseStrBuilder.append(inputStr);
        }

        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
        String param = null;
        if(jsonObject != null){
            param = jsonObject.toJSONString();

        }
        //记录日志
        log.info("请求开始，url:{},params:{}",url,param);
        return true;
    }
}
