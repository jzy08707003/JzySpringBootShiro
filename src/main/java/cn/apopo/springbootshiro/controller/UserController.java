/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qiaoshuang on 2017/1/4.
 */
@Controller
@RequestMapping("user")
public class UserController {
    /**
     * 用户查询
     *
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("user:view") // 权限管理
    public String userInfo(){
        return "users";
    }

    /**
     * 用户添加
     *
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("user:add") // 权限管理
    public String userInfoAdd(){
        return "userAdd";
    }
    /**
     * 用户删除
     *
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("user:del") // 权限管理
    public String userDel(){
        return "userDel";
    }
}
