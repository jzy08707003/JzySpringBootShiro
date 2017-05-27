/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package cn.apopo.springbootshiro.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.apopo.springbootshiro.domain.User;

/**
 * Created by zhenyujin on 2017/05/24.
 */
@Controller
public class HomeController {

    @RequestMapping({"/", "index"})
    public String index(Model model, Map<String, Object> map, @ModelAttribute("user") User user) {
        model.addAttribute("index", "thymeleaf");

        Map<String, String> aMap = new HashMap<>();
        aMap.put("key", "value");
        map.put("map", aMap);

        user.setName("aName");

        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) {
        // 登录失败从request中获取shiro处理的异常信息
        // shiroLoginFailure:就是shiro异常类的全类名
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "登录成功";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                msg = "UnknownAccountException -->帐号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + exception;
            }
        }else{
        	return "myHome";//因为使用了shiro过滤器 会被拦截并重定向到配置中的文件
        }
        map.put("msg", msg);
        
        // 此方法不处理登录成功,由shiro进行处理.
        return "/login";
    }
}
