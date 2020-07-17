package com.ten.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClsaaName UserApplication
 * Version information 1.0
 * @Date 2020/7/14 09:01
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ten.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
