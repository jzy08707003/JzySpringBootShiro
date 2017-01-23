/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service;

import cn.apopo.springbootshiro.domain.User;

/**
 * Created by qiaoshuang on 2017/1/4.
 */
public interface IUserService {

    User findByUserName(String username);
}
