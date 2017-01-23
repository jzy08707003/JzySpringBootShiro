/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service;

import java.util.List;

import cn.apopo.springbootshiro.domain.Role;

/**
 * Created by qiaoshuang on 2017/1/5.
 */
public interface IRoleService {

    List<Role> getRolesWithPermissionByUserId(Long userId);
}
