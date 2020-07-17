package com.ten.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import util.IdWorker;

/**
 * @ClsaaName ArticleApplication
 * Version information 1.0
 * @Date 2020/7/10 15:40
 */
@SpringBootApplication
@MapperScan("com.ten.article.dao")
@EnableEurekaClient
@CrossOrigin
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }

    @Bean
    public IdWorker createIdWorker() {
        return new IdWorker(1, 1);
    }
}
