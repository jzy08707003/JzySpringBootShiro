/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.apopo.springbootshiro.configuration.shiro.ShiroConfiguration;
import cn.apopo.springbootshiro.domain.User;
import cn.apopo.springbootshiro.mapper.UserMapper;
import cn.apopo.springbootshiro.service.IRoleService;
import cn.apopo.springbootshiro.service.IUserService;

/**
 * Created by zhenyujin on 2017/05/24.
 */
@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
    @Resource
    private IRoleService roleService;

    @Resource
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String username) {
        logger.info("UserServiceImpl.findByUsername()");
        User user = userMapper.findbyUserName(username);
        user.setRoleList(roleService.getRolesWithPermissionByUserId(user.getId()));
        return user;
    }
}
