package com.jun.sail.apollo.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 通过ConfigurationProperties加载配置，可以省去前缀
 * 但是注意需要去配置刷新这个bean
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis.cache")
@RefreshScope
public class SampleRedisConfig {

    private int expireSeconds;
    private int commandTimeout;

}