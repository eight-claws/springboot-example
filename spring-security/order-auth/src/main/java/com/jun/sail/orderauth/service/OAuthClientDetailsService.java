package com.jun.sail.orderauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class OAuthClientDetailsService extends JdbcClientDetailsService {

    /**
     * 这里的表是使用默认的，可以覆盖修改此类，定义自己的表结构
     */
    public OAuthClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 根据clientId获取到client详情
     * 可以增加一层缓存
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return super.loadClientByClientId(clientId);
    }
}
