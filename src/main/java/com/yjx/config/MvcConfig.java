package com.yjx.config;

import com.yjx.interceptor.NoLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NoLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/index",
                        "/user/login",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/lib/**",
                        "/error"
                );
    }
}
