package com.jun.sail.orderauth.oauth.service;

import com.jun.sail.orderauth.entity.UserAccountEntity;
import com.jun.sail.orderauth.mapper.UserAccountMapper;
import com.jun.sail.orderauth.oauth.OAuthUserDetailsResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 存储用户信息的表
 * 刷新token接口必须要有该UserDetailsService
 */
@Service
@Slf4j
public class UserAccountService implements UserDetailsService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 根据用户名获取UserDetails（重写Oauth中方法）
     *
     * @throws UsernameNotFoundException 抛出异常
     */
    @Override
    public OAuthUserDetailsResponse loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountEntity userAccount = userAccountMapper.findOne(username);

        if (Objects.nonNull(userAccount)) {
            OAuthUserDetailsResponse oAuthUserDetailsResponse = new OAuthUserDetailsResponse();
            BeanUtils.copyProperties(userAccount, oAuthUserDetailsResponse);
            return oAuthUserDetailsResponse;
        }
        return null;
    }


}
