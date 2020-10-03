package com.jun.sail.orderapi.config;

import com.jun.sail.orderapi.config.props.OrderApiProperties;
import com.jun.sail.orderapi.oauth.CustomAuthResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String SCOPE_OPEN_API = "openapi";

    @Autowired
    private OrderApiProperties orderApiProperties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(new CustomAuthResponseExceptionTranslator());
        resources.authenticationEntryPoint(authenticationEntryPoint)
                .resourceId(SCOPE_OPEN_API)
                .tokenServices(tokenServices()).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                // 我的理解是这里开启暴露/openapi的url
                        requestMatchers().antMatchers("/openapi/**")
                .and()
                .authorizeRequests()
                // 这里配置/openapi开头的endpoints需要openapi的scope权限
                .antMatchers("/openapi/**").access("#oauth2.hasScope('" + SCOPE_OPEN_API + "')");
        //        http.exceptionHandling()
//                .accessDeniedHandler(customAccessDeniedHandler)
//                .authenticationEntryPoint(customAuthenticationEntryPoint);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true); // support refresh token，default false
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    /**
     * 这里的TokenStore保持和认证服务里的一致
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(orderApiProperties.getOauthJwtSecret());
        return converter;
    }

}