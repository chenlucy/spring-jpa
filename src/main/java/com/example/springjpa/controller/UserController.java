package com.example.springjpa.controller;

import com.example.springjpa.compoment.Result;
import com.example.springjpa.entity.User;
import com.example.springjpa.server.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Describe
 * @Author Corey
 * @Date 2018/7/11.
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "findAll")
    public Result findAll() {
        return Result.genSuccessResult(userService.findAll());
    }

    @RequestMapping(value = "getById")
    public User getById(Integer id) {
        return userService.getById(id);
    }
}
