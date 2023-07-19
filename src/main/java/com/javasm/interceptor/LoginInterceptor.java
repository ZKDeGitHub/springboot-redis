package com.javasm.interceptor;

import com.javasm.common.AppProperties;
import com.javasm.common.AuthenticationException;
import com.javasm.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 15:20
 * @description: 使用前置拦截器，拦截除了登陆的方法，进行校验，只有token校验过了才能登陆
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private AppProperties appProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求的头部信息获取token
        String user_token = request.getHeader("user_token");

        if(user_token == null || user_token.trim().length() == 0){
            throw new AuthenticationException("尚未登录");
        }
        // 对token进行解密校验，是否正确
        String parseToken = JwtUtil.parseToken(user_token, appProperties.getPrivateKey());
        if(parseToken == null){
            throw new AuthenticationException("尚未登录");
        }
        return true;
    }
}
