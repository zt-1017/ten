package com.ten.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import util.IdWorker;

/**
 * @ClsaaName BaseApplication
 * Version information 1.0
 * @Date 2020/7/15 00:16
 */
@SpringBootApplication
@MapperScan("com.ten.article.dao")
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
