package com.javasm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.common.AppProperties;
import com.javasm.entity.User;
import com.javasm.mapper.UserMapper;
import com.javasm.service.UserService;
import com.javasm.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 11:21
 * @description:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AppProperties appProperties;

    /**
     * 根据用户名获取User对象
     * @param userName
     * @return
     */
    @Override
    public User getByName(String userName) {

        // 非空校验
        Objects.requireNonNull(userName);

        //根据用户名获取到User对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            //log记录
        }
        return user;
    }

    @Override
    public String getToken(User user) {
        String token = JwtUtil.getToken(user.getUserName(), appProperties.getPrivateKey(), appProperties.getExpireTime());
        return token;
    }
}
