/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.apopo.springbootshiro.domain.Role;
import cn.apopo.springbootshiro.mapper.RoleMapper;
import cn.apopo.springbootshiro.service.IPermissionService;
import cn.apopo.springbootshiro.service.IRoleService;

/**
 * Created by qiaoshuang on 2017/1/5.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IPermissionService permissionService;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesWithPermissionByUserId(Long userId) {
        List<Role> roles = roleMapper.getByUserId(userId);
        for (Role role : roles) {
            role.setPermissions(permissionService.getPermissionsByRoleId(role.getId()));
        }
        return roles;
    }
}
