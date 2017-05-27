/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.mapper;

import java.util.List;

import cn.apopo.springbootshiro.domain.Role;

/**
 * Created by zhenyujin on 2017/05/24.
 */
public interface RoleMapper {

    List<Role> getByUserId(Long userId);
}
