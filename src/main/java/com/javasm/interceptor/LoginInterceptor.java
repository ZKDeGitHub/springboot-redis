package com.javasm.interceptor;

import com.javasm.common.AppProperties;
import com.javasm.common.AuthenticationException;
import com.javasm.util.CacheService;
import com.javasm.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 15:20
 * @description: 使用前置拦截器，拦截除了登陆的方法，进行校验，只有token校验过了才能登陆
 */

public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private AppProperties appProperties;

    @Resource
    private CacheService cacheService;

    /**
     * 校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
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

        /*
        * token过期时，先查询redis缓存中是对应的key值是否存在（key值有数据说明逻辑上的有效期没有过期）
        * 如果存在，则重新签发token，并返回给客户端
        */
        String redisToken = null;
        Integer expireTime = appProperties.getExpireTime();
        String privateKey = appProperties.getPrivateKey();
        if(parseToken.equals(JwtUtil.EXPIREDJWTEXCEPTION)){
            // 先从redis中查询是否有此token
            redisToken = cacheService.getRedisValue(CacheService.TOKEN_CACHE_NAME + user_token);

            // 若查询不到值，说明redis中的token也过期了，抛出异常
            if(redisToken == null){
                throw new AuthenticationException("token已经过期了，请重新登陆");
            }

            // 若查询到值，说明reids中的token未过期，
            // 重新签发token，并返回到客户端的头部信息中
            String token = JwtUtil.getToken(redisToken, privateKey, expireTime);
            response.setHeader("user_token",token);
        }
        // 前面的校验都通过了，每次请求都将redis中的token过期时间延长，延长的值为设置的token过期时长
        cacheService.set(CacheService.TOKEN_CACHE_NAME + user_token,redisToken,expireTime + 1, TimeUnit.MINUTES);
        return true;
    }
}
