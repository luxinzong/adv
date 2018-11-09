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
