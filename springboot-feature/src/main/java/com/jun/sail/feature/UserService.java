package com.jun.sail.feature;

import com.jun.sail.feature.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author wangjun
 * @Date 2021/2/7
 **/
@Service
@Slf4j
public class UserService {

    @Cacheable("user")
    public UserDto getUserById(String userId) {
        UserDto userDto = UserDto.builder().id(userId).name(userId + " 名字").age(15).build();
        log.info("user: {}", userDto);
        return userDto;
    }

    @Cacheable("user")
    public UserDto findUserByName(String name) {
        UserDto userDto = UserDto.builder().name(name + " 名字").age(15).build();
        log.info("user: {}", userDto);
        return userDto;
    }

}
