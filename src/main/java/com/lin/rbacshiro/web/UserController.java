package com.lin.rbacshiro.web;

import com.alibaba.fastjson.JSON;
import com.lin.rbacshiro.dto.Response;
import com.lin.rbacshiro.service.IUserService;
import com.lin.rbacshiro.util.JwtUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login() {
        return JwtUtil.encode("123");
    }

    @RequestMapping("/users")
    public String users() {
        return "ok";
    }

    @RequestMapping("/admin")
    @RequiresRoles(value = {"ROLE_ADMIN"}, logical = Logical.AND)
    public String admin() {
        return "admin";
    }

    @RequestMapping("/401")
    public String unauthorized() {
        Response response = new Response();
        response.setCode(401);
        response.setMessage("unauthorized");
        response.setData(null);
        return JSON.toJSONString(response);
    }
}
