package com.ten.user.controller;

import com.ten.user.pojo.User;
import com.ten.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClsaaName UserController
 * Version information 1.0
 * @Date 2020/7/14 09:13
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        User result = userService.login(user);
        if (result != null) {
            return new Result(true, StatusCode.OK, "登录成功！");
        }

        return new Result(false, StatusCode.OK, "登录失败！");
    }

    //get user/{userId}根据id查询用户
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String userId) {
        User user = userService.findById(userId);
        return new Result(true, StatusCode.OK, "查询成功！", user);
    }


}
