/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service;

import cn.apopo.springbootshiro.domain.User;

/**
 * Created by zhenyujin on 2017/05/24.
 */
public interface IUserService {

    User findByUserName(String username);
}
