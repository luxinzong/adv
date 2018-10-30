package com.suma.realm;

import com.suma.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/24 0024
 * @Description 重写授权代码，解决跨域问题
 **/
@Slf4j
public class AuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
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

        HttpServletResponse httpResp = WebUtils.toHttp(response);
        HttpServletRequest httpReq = WebUtils.toHttp(request);

        /**系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*/
        httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin"));
//        httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("*"));
        httpResp.addHeader("Access-Control-Allow-Headers", "*");
        httpResp.addHeader("Access-Control-Allow-Methods", "*");
        httpResp.addHeader("Access-Control-Allow-Credentials", "true");

        return super.onAccessDenied(request,response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean allowed = super.isAccessAllowed(request,response,mappedValue);
        if(!allowed){
            String method = WebUtils.toHttp(request).getMethod();
            String url  =WebUtils.toHttp(request).getRequestURI();
            log.info("请求方法:" + method + ",请求地址:" + url);
            if(StringUtils.equalsIgnoreCase("OPTIONS",method)){
                log.info("此次方法请求方式是:" + "OPTIONS");
                return true;
            }
        }

        return allowed;
    }
}
