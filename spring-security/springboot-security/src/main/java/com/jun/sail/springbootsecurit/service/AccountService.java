package com.jun.sail.springbootsecurit.service;

import com.jun.sail.springbootsecurit.endpoint.dto.OAuthUserDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 自定义的UserDetailsService，负责从数据库查询用户信息
 */
@Slf4j
@Service
public class AccountService implements UserDetailsService {


    /**
     * 根据用户名获取UserDetails（重写Oauth中方法）
     *
     * @throws UsernameNotFoundException
     */
    @Override
    public OAuthUserDetailsResponse loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查
        //UserEntity userEntity = UserMapper.findOne(username);
        OAuthUserDetailsResponse result = new OAuthUserDetailsResponse();
        result.setUuid("u1");
        result.setPassword("$2a$10$8FygjsGSXDB9FYqgVrkQwueDnQwrOB90iqa04MA46wFMbOZragVZa");//利用BCrypt对123456加密，前端需要输入123456
        return result;
    }


}