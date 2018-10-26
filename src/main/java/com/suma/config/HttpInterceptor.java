package com.suma.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        //获取请求方式
        System.out.println(httpServletRequest.getMethod());
        //获取项目名称
        System.out.println(httpServletRequest.getContextPath());
        //获取完整请求路径
        System.out.println(httpServletRequest.getRequestURL());
        //获取除了域名外的请求数据
        System.out.println(httpServletRequest.getRequestURI());
        //获取请求参数
        System.out.println(httpServletRequest.getQueryString());
        System.out.println("----------------------------------------------------------");
        //获取请求头
        String header = httpServletRequest.getHeader("user-Agent");
        System.out.println(header);
        header = header.toLowerCase();
        //根据请求头数据判断浏览器类型
        if(header.contains("chrome")){
            System.out.println("您的访问浏览器为谷歌浏览器");
        }else if(header.contains("msie")){
            System.out.println("您的访问浏览器为IE浏览器");
        }else if(header.contains("firefox")){
            System.out.println("您的访问浏览器为火狐浏览器");
        }else{
            System.out.println("您的访问浏览器为其它浏览器");
        }
        System.out.println("----------------------------------------------------------");
        //获取所有的消息头名称
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出
        while(headerNames.hasMoreElements()){
            String nextElement = headerNames.nextElement();
            System.out.println(nextElement+":"+httpServletRequest.getHeader(nextElement));
        }
        System.out.println("----------------------------------------------------------");
        //根据名称获取此重名的所有数据
        Enumeration<String> headers = httpServletRequest.getHeaders("accept");
        while (headers.hasMoreElements()) {
            String string = (String) headers.nextElement();
            System.out.println(string);
        }

        //获得请求url
        String url = httpServletRequest.getRequestURI();
        //获取json传输参数
        Map<String,String[]> map = httpServletRequest.getParameterMap();
        String requestMap = JSON.toJSONString(map);

        log.info("请求开始，url:{},params:{}",url,requestMap);
        return true;
    }
}
