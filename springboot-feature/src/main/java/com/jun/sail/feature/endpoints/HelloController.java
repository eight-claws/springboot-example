package com.jun.sail.feature.endpoints;

import com.jun.sail.feature.UserService;
import com.jun.sail.feature.dto.UserDto;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wangjun
 * @Date 2020/10/27
 **/
@RestController
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public UserDto findUserByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }

}
