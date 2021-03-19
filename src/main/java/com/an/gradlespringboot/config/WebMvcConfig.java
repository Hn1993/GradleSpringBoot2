package com.an.gradlespringboot.config;


import com.an.gradlespringboot.intercept.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置文件
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截 /user 路径 (验证token)
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/public/**");
    }

}
