package com.xidian.blog.config;

import com.xidian.blog.interceptor.AdminLoginInterceptor;
import com.xidian.blog.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 米
 * @date 2020/1/17
 */
@Configuration
public class BlogCommonConfigurer implements WebMvcConfigurer {
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/page/**")
//                .excludePathPatterns("/common/**")
//                .excludePathPatterns("/dist/**")
//                .excludePathPatterns("/admin/**")
//                .excludePathPatterns("/admin/dist/**")
//                .excludePathPatterns("/admin/plugins/**")
//                .excludePathPatterns("/user/loginByEmail")
//                .excludePathPatterns("/upload")
//                .excludePathPatterns("/upload/**")
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/user.html")
//                .excludePathPatterns("/**")
//                .excludePathPatterns("/plugins/**");
    }
}
