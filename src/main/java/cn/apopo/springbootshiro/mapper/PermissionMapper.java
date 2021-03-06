/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.mapper;

import java.util.List;

import cn.apopo.springbootshiro.domain.Permission;

/**
 * Created by zhenyujin on 2017/05/24.
 */
public interface PermissionMapper {

    List<Permission> getByRoleId(Long roleId);
}
