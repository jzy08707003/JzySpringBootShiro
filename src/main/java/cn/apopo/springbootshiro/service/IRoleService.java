/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service;

import java.util.List;

import cn.apopo.springbootshiro.domain.Role;

/**
 * Created by zhenyujin on 2017/05/24.
 */
public interface IRoleService {

    List<Role> getRolesWithPermissionByUserId(Long userId);
}
