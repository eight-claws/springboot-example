package com.jun.sail.feature.conf.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("data.redis")
@PropertySource(value = "classpath:data/read/test.properties", ignoreResourceNotFound = true) // 注意文件路径 data.redis可能是一个文件夹名字
//@PropertySource(value = "file://${CONFIG_HOME}/data/redis/test.properties", ignoreResourceNotFound = true)
public class DataProperties {

    private UserProps user;

    private Device connect;
    private String oauthJwtSecret;

    private List<Device> devices;

    @Getter
    @Setter
    public static class UserProps {
        private String email;
        private String countryCode;
        private String password;
    }

    @Getter
    @Setter
    public static class Device {
        private String uuid;
        private String pid;
    }
}