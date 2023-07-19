package com.javasm.config;

import com.javasm.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 16:22
 * @description: 拦截器配置类
 */

public class MvcConfig implements WebMvcConfigurer {

   @Resource
   private LoginInterceptor loginInterceptor;

   /**
    * 添加LoginInterceptor拦截器
    * @param registry
    */
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login");
   }
}
