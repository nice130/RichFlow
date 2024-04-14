package com.richflow.api.config;

import com.richflow.api.interceptor.LoginInterceptor;
import com.richflow.api.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("config");
        registry.addInterceptor(new LoginInterceptor(this.tokenProvider))
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/users/login", "/users/join", "/js/**", "/css/**", "/images/**","/error","/favicon.ico");
//            .excludePathPatterns("/**"); // 개발용
    }
}