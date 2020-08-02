package com.jun.sail.orderauth.config;

import com.jun.sail.orderauth.oauth.JwtTokenEnhancer;
import com.jun.sail.orderauth.config.props.OrderAuthProperties;
import com.jun.sail.orderauth.oauth.service.OAuthClientDetailsService;
import com.jun.sail.orderauth.oauth.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private static final String DEMO_RESOURCE_ID = "openapi";

    @Autowired
    private OrderAuthProperties orderAuthProperties;

    /**
     * 注意这里AuthenticationManager和UserAccountService
     * 是在SecurityConfiguration配置的，把俩个配置类关联了起来
     */
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private PasswordEncoder oauthClientSecretEncoder;
    @Autowired
    private OAuthClientDetailsService oAuthClientDetailsService;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .realm("oauth2-resources")
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(oauthClientSecretEncoder)
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userAccountService) //这里和SecurityConfiguration的userAccountService也可以不一样，为什么？
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain);
    }

    /**
     * 配置客户端的校验
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
        clients.withClientDetails(oAuthClientDetailsService);

        // 这里将第三方客户端放到内存里，配置两个客户端,一个用于client认证
//        clients.inMemory()
//                .withClient("client_1")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("select")
//                .authorities("client")
//                .secret("123456")
//                .and().withClient("client_2")
//                .secret("123456")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("authorization_code", "code", "password", "refresh_token")
//                .scopes("select")
//                .redirectUris("http://www.baidu.com");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(orderAuthProperties.getOauthJwtSecret());
        return converter;
    }

    @Bean
    public JwtTokenEnhancer tokenEnhancer() {
        return new JwtTokenEnhancer();
    }

}