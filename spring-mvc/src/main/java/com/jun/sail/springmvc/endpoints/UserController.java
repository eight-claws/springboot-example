package com.jun.sail.springmvc.endpoints;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Jun
 * 创建时间： 2019/9/28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/testLog")
    public String testlogger() {
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");

        return "成功";
    }
}


