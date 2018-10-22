package com.suma.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        //获取json传输参数
        Map<String,String[]> map = request.getParameterMap();
        String requestMap = JSON.toJSONString(map);

        log.info("请求开始，url:{},params:{}",url,requestMap);
        return true;
    }
}
