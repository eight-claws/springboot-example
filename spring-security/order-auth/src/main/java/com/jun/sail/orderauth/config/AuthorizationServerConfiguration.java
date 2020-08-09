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
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
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
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private PasswordEncoder oauthClientSecretEncoder;
    @Autowired
    private OAuthClientDetailsService oAuthClientDetailsService;
    @Autowired
    private DataSource dataSource;

    /**
     * 配置客户端的信息，可以从内存中加载，也可以从数据库加载（更常用）
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


    /**
     * 定义token endpoint的安全配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .realm("oauth2-resources")
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(oauthClientSecretEncoder)
                .allowFormAuthenticationForClients();
    }

    /**
     * 定义授权、token endpoint和token服务
     * <p>
     * 这里最重要的就是DefaultTokenServices提供随机数来返回access token和refresh token，
     * 可自定义tokenEnhancer来改变token值，它会在token生成后/保存前调用
     * <p>
     *
     *
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 这里设置了两个TokenEnhancer
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.authenticationManager(authenticationManager)// 注入authenticationManager开启密码授权模式
                // 必须要配置userDetailsService，才支持refresh token grant，to ensure that the account is still active
                // 这里和SecurityConfiguration的userAccountService也可以不一样，为什么？
                .userDetailsService(userAccountService)
                .authorizationCodeServices(authorizationCodeServices)// 定义authorizationCodeServices支持auth code grant.
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain);
    }

    /**
     * 改变token的值，如添加自定义信息
     */
    @Bean
    public JwtTokenEnhancer tokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    /**
     * JWT提供的，帮助把OAuth认证信息转为JWT，即access_token,它返回的很多默认字段（jti,ati)都是在这里定义的
     * 注意它本身也是一个TokenEnhancer
     *
     * 这里使用的密钥也要定义在资源服务里，以便资源服务也可以校验token
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(orderAuthProperties.getOauthJwtSecret());
        return converter;
    }


    /**
     * 定义token的存储方式：可以放在redis/数据库（oauth_access_token表）/内存，或者jwt中
     * 这里放在JWT里，根本就不必后端存储token了，这是JWT很大的优势
     *
     * 但是JWT也有缺点，1.不容易撤销授权，所以一般令牌时效性很短，撤销授权可以在刷新时实现，怎么实现？
     * 2.如果要存储的信息很多，令牌会变得很大
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


}