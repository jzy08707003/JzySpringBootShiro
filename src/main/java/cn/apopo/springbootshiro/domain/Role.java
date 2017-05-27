/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色实体类
 *
 * Created by zhenyujin on 2017/1/3.
 */
public class Role implements Serializable{

    private static final long serialVersionUID = 2231705667121768303L;

    // 编号
    private Long id;

    // 角色标识 程序中判断使用,如"admin",这个是唯一的
    private String role;

    // 角色描述,UI界面显示使用
    private String description;

    // 是否可用,如果不可用将不会添加给用户
    private Boolean available = Boolean.FALSE;

    //角色 -- 权限关系：多对多关系
    private List<Permission> permissions;

    // 用户 - 角色关系定义;
    private List<User> userInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<User> userInfos) {
        this.userInfos = userInfos;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
                + ", permissions=" + permissions + "]";
    }
}
