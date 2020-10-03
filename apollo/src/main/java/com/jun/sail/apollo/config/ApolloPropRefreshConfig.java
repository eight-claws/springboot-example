package com.jun.sail.apollo.config;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.jun.sail.apollo.config.prop.SampleRedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@EnableApolloConfig
public class ApolloPropRefreshConfig {
    private static final Logger logger = LoggerFactory.getLogger(ApolloPropRefreshConfig.class);

    private final RefreshScope refreshScope;
    private final SampleRedisConfig sampleRedisConfig;

    public ApolloPropRefreshConfig(RefreshScope refreshScope, SampleRedisConfig sampleRedisConfig) {
        this.refreshScope = refreshScope;
        this.sampleRedisConfig = sampleRedisConfig;
    }

    /**
     * 用于通过@ConfigurationProperties(prefix = "redis.cache")的配置类自动刷新
     * 这里监听到相应的key变化，就去刷新对应的bean.不支持根据prefix匹配，如果不指定就是监听namespace所有的key变化
     */
    @ApolloConfigChangeListener(value = {ConfigConsts.NAMESPACE_APPLICATION},
            interestedKeys = {"redis.cache.commandTimeout"})
    public void onChange(ConfigChangeEvent changeEvent) {
        logger.info("before refresh {}", sampleRedisConfig.toString());
        refreshScope.refresh("sampleRedisConfig");
        logger.info("after refresh {}", sampleRedisConfig.toString());
    }
}
