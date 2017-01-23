/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.apopo.springbootshiro.domain.User;
import cn.apopo.springbootshiro.mapper.UserMapper;
import cn.apopo.springbootshiro.service.IRoleService;
import cn.apopo.springbootshiro.service.IUserService;

/**
 * Created by qiaoshuang on 2017/1/4.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IRoleService roleService;

    @Resource
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String username) {
        System.out.println("UserServiceImpl.findByUsername()");
        User user = userMapper.findbyUserName(username);
        user.setRoleList(roleService.getRolesWithPermissionByUserId(user.getId()));
        return user;
    }
}
