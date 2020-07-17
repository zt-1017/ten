package com.ten.notice.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ten-user")
public interface UserClient {
    //get user/{userId}根据id查询用户
    @RequestMapping(value = "user/{userId}",method = RequestMethod.GET)
    Result findById(@PathVariable("userId") String userId);
}
