/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.apopo.springbootshiro.domain.Permission;
import cn.apopo.springbootshiro.mapper.PermissionMapper;
import cn.apopo.springbootshiro.service.IPermissionService;

/**
 * Created by qiaoshuang on 2017/1/5.
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionsByRoleId(Long roleId) {
        return permissionMapper.getByRoleId(roleId);
    }
}
