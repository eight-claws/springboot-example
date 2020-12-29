package com.jun.sail.feature.conf.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ConfigurationProperties 用于将主配置文件(application.properties或者“application.yml” )中的属性，映射到实体类中对应的属性。 意思就是把主配置文件中配置属性设置到对应的Bean属性上。
 * PropertySource 加载指定的属性文件的配置到Spring的Environment中
 * 所以@PropertySource指定加载哪个文件，@ConfigurationProperties指定加载文件中的哪一类属性， 在一起解决了@ConfigurationProperties只能加载主文件内属性问题。
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties("data.redis")
@PropertySource(value = "classpath:data/read/test.properties", ignoreResourceNotFound = true) // 注意文件路径 data.redis可能是一个文件夹名字
@PropertySource(value = "file://${CONFIG_HOME}/data/redis/test.properties", ignoreResourceNotFound = true)
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
