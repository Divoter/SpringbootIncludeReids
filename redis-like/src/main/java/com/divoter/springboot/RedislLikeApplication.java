package com.divoter.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedislLikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedislLikeApplication.class, args);
    }
}
