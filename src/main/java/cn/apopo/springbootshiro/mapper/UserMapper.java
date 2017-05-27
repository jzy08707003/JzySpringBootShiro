/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.mapper;

import cn.apopo.springbootshiro.domain.User;

/**
 * User持久化
 *
 * Created by zhenyujin on 2017/05/24.
 */
public interface UserMapper {

    // 通过username查找用户信息
    User findbyUserName(String username);
}
