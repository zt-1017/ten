package com.ten.notice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @ClsaaName NoticeApplication
 * Version information 1.0
 * @Date 2020/7/16 14:27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class NoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

    @Bean
    public IdWorker careatIdWorker(){
        return new IdWorker(1, 1);
    }
}
