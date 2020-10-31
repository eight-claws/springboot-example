package com.jun.sail.springmvc.endpoints;


import com.jun.sail.springmvc.dto.CountGroupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    private List<CountGroupDto> geCountGroupDtoList() {
        List<CountGroupDto> countGroupDtoList = new ArrayList<>();
        CountGroupDto countGroupDto;
        for (int i = 0; i < 10; i++) {
            countGroupDto = new CountGroupDto();
            countGroupDto.setName("小于" + i);
            countGroupDto.setType("金额模式");
            countGroupDto.setRegionCode("浙江");
            countGroupDtoList.add(countGroupDto);
        }
        return countGroupDtoList;
    }

}


