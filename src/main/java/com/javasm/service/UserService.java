package com.javasm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javasm.entity.User;

/**
 * @Author: zk
 * @since: 11
 * @Date 2023/7/19 11:20
 * @description:
 */

public interface UserService extends IService<User> {

    User getByName(String userName);

    String getToken(User user);
}
