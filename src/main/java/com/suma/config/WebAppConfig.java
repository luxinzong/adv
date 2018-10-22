package com.suma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/17 0017
 * @Description
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/info/**");

//        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/system/**");

        //需要统一日志处理，在这里加路径
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/system/role/**");
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/location/**");
    }
}
