package com.suma.config;

import com.alibaba.fastjson.JSON;
import com.suma.pojo.AdvUser;
import com.suma.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
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
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws Exception {
        //获得请求url
        String url = httpServletRequest.getRequestURI();
        //获取json传输参数
        Map<String,String[]> map = httpServletRequest.getParameterMap();
        String requestMap = JSON.toJSONString(map);
        log.info("请求开始，url:{},params:{}",url,requestMap);


        return true;
    }


}
