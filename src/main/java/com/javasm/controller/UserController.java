package com.javasm.controller;

import com.javasm.common.AuthenticationException;
import com.javasm.entity.User;
import com.javasm.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 10:48
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;


    /**
     * 获取值时，需要校验是否登陆
     * 用拦截器
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public User getById(Integer id){
        User user = userService.getById(id);
        return user;
    }


    /**
     * 第一次登陆时做校验，并签发token
     * @param user
     * @return
     */
    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user){
        if(user != null){
            /*
            * 1 校验用户是否存在
            */
            String userName = user.getUserName();
            String password = user.getPassword();

            if(userName !=null && userName.trim().length() > 0 && password != null && password.trim().length() > 0){
                // 校验用户名是否存在
                User dbUser = userService.getByName(userName);
                if(dbUser == null){
                    //抛出异常代处理
                    throw new AuthenticationException("用户名错误");
                }

                // 校验密码是否正确
                if(!dbUser.getPassword().equals(password)){
                    throw new AuthenticationException("密码错误");
                }

                /*
                * 2 校验通过，签发token
                */
                String token = userService.getToken(user);
                //返回给浏览器 把token信息放入响应头
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set("user_token", token);
                return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(null);
            }
        }
        throw new AuthenticationException("用户信息为null");

    }
}
