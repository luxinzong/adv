package com.suma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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


        //需要统一日志处理，在这里加路径
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/system/role/**");
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/login");
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/location/**");
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/system/menu/**");
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/system/user/**");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);

    }
}
