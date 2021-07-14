package com.example.demomsgqueueredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@EnableScheduling
@EnableRedisRepositories
@SpringBootApplication
public class DemoMsgQueueRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMsgQueueRedisApplication.class, args);
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return new ScheduledThreadPoolExecutor(10);
    }
}
