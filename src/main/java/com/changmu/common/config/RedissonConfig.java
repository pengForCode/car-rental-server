package com.changmu.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yu peng
 * @version 1.0
 * @date 2022/4/27
 * @description redis配置
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //单节点
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if (StringUtils.isEmpty(password)) {
            config.useSingleServer().setPassword(null);
        } else {
            config.useSingleServer().setPassword(password);
        }
        return Redisson.create(config);
    }
}
