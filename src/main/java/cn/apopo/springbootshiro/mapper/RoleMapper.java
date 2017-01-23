/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.mapper;

import java.util.List;

import cn.apopo.springbootshiro.domain.Role;

/**
 * Created by qiaoshuang on 2017/1/5.
 */
public interface RoleMapper {

    List<Role> getByUserId(Long userId);
}
