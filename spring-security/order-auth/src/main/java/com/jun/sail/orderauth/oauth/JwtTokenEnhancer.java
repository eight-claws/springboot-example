package com.jun.sail.orderauth.oauth;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来改变accessToken值，添加自定义的信息
 * 这个Enhancer会在accessToken生成后/保存前被调用
 */
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 往jwt添加自定义信息，比如从数据库加载用户id放到jwt形式的accessToken里。
        // 如果不是jwt，也可以在此改变accessToken的值
        Map<String, Object> info = new HashMap<>();
        info.put("age", 67);
        info.put("country", "china");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }

}
