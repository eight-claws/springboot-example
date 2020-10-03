package com.jun.sail.apollo.endpoints;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import com.jun.sail.apollo.config.prop.SampleRedisConfig;
import com.jun.sail.apollo.config.prop.TestJavaConfigBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class PropertiesController {

    private TestJavaConfigBean javaConfigBean;
    private SampleRedisConfig sampleRedisConfig;

    @Value("${timeout:5000}")
    private int timeout;

    @ApolloConfig("application")
    private Config anotherConfig; //inject config for namespace application


    public PropertiesController(TestJavaConfigBean javaConfigBean, SampleRedisConfig sampleRedisConfig) {
        this.javaConfigBean = javaConfigBean;
        this.sampleRedisConfig = sampleRedisConfig;
    }

    @RequestMapping("/getTimeout")
    public int getConfig() {
        return timeout;
    }

    @RequestMapping("/getBatch")
    public int getBatch() {
        return javaConfigBean.getBatch();
    }

    @RequestMapping("/getRedis")
    public String getRedis() {
        String timeout = anotherConfig.getProperty("timeout", "4000");
        return sampleRedisConfig.getCommandTimeout() + " " + sampleRedisConfig.getExpireSeconds();
    }
}
