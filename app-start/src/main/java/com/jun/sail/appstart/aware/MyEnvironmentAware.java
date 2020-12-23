package com.jun.sail.appstart.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.Properties;

@Configuration
// @ConditionalOnExpression("!'pro'.equals(environment['env'])")
public class MyEnvironmentAware implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        // 获取环境变量
        Map<String, String> env = System.getenv();

        // 获取系统属性
        Properties properties = System.getProperties();

        System.out.println(properties);
    }
}