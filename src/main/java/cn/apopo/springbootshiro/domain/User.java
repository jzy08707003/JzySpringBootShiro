/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 *
 * Created by qiaoshuang on 2017/1/3.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2385906340787265806L;

    // 用户id
    private long id;

    // 账号
    private String userName;

    // 名称（昵称或者真实姓名，不同系统不同定义）
    private String name;

    // 密码
    private String password;

    // 加密密码的盐
    private String salt;

    // 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private byte state;

    // 一个用户具有多个角色
    private List<Role> roleList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    // 对盐进行定义,用户名+salt,提高破解难度
    public String getCredentialsSalt() {
        return this.userName + this.salt;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", name=" + name + ", password=" + password + ", salt="
                + salt + ", state=" + state + "]";
    }
}

