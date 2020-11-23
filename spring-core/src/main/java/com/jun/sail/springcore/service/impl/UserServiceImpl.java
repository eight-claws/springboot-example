package com.jun.sail.springcore.service.impl;

import com.jun.sail.springcore.service.UserDto;
import com.jun.sail.springcore.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author wangjun
 * @Date 2020/11/23
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto saveUser(UserDto userDto) {
        System.out.println("save user");
        return userDto;
    }
}
