package com.ten.article.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClsaaName BaseExceptionHander  公共异常处理类
 * Version information 1.0
 * @Date 2020/7/10 20:36
 */
@ControllerAdvice
public class BaseExceptionHander {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result hander(Exception e) {

        System.out.println("处理异常！");

        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
