package com.jun.sail.orderauth.oauth.service;

import com.jun.sail.orderauth.entity.OauthCodeEntity;
import com.jun.sail.orderauth.mapper.OauthCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 对应着 T_OAUTH_CODE表，存储code
 */
@Component
public class AuthorizationCodeServices extends RandomValueAuthorizationCodeServices {
    @Autowired
    private OauthCodeMapper oauthCodeMapper;

    private static final int AUTH_CODE_VALID_TIME = 5;//expired after 5 min

    JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        OauthCodeEntity oauthCodeEntity = new OauthCodeEntity();
        oauthCodeEntity.setCode(code);
        oauthCodeEntity.setAuthentication(Base64Utils.encodeToString(serializer.serialize(oAuth2Authentication)));
        oauthCodeMapper.insert(oauthCodeEntity);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        OauthCodeEntity oauthCodeEntity = oauthCodeMapper.findValidOneByCode(code, LocalDateTime.now().minusMinutes(AUTH_CODE_VALID_TIME));
        if (Objects.nonNull(oauthCodeEntity)) {
            oauthCodeMapper.deleteById(oauthCodeEntity.getId());
            return (OAuth2Authentication) serializer.deserialize(Base64Utils.decodeFromString(oauthCodeEntity.getAuthentication()));
        }
        return null;
    }

}
