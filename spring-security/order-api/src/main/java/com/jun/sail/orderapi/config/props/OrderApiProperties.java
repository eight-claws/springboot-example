package com.jun.sail.orderapi.config.props;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("order.oauth")
@PropertySource(value = "classpath:order.api/orderApi.properties", ignoreResourceNotFound = true)
public class OrderApiProperties {

    private String oauthJwtSecret;

    public String getOauthJwtSecret() {
        return oauthJwtSecret;
    }
}
