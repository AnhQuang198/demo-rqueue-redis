package com.example.demomsgqueueredis.configs;

import com.github.sonus21.rqueue.config.SimpleRqueueListenerContainerFactory;
import com.github.sonus21.rqueue.models.enums.PriorityMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RqueueConfiguration {

    @Bean
    public SimpleRqueueListenerContainerFactory simpleRqueueListenerContainerFactory() {
        SimpleRqueueListenerContainerFactory factory = new SimpleRqueueListenerContainerFactory();

        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(System.getenv("REDIS_HOST"), Integer.parseInt(System.getenv("REDIS_PORT")));
        redisConfig.setDatabase(Integer.parseInt(System.getenv("REDIS_JOB_DB")));
        LettuceConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(redisConfig);
        redisConnectionFactory.afterPropertiesSet();
        factory.setRedisConnectionFactory(redisConnectionFactory);
        factory.setPriorityMode(PriorityMode.STRICT);

        return factory;
    }

}
