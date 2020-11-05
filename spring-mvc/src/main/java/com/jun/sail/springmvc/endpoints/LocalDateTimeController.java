package com.jun.sail.springmvc.endpoints;

import com.jun.sail.springmvc.dto.LocalDateTimeDto;
import com.jun.sail.springmvc.web.returnvalue.SailResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author wangjun
 * @Date 2020/11/5
 **/
@RestController
@RequestMapping("/time")
public class LocalDateTimeController {

    @SailResponse("查询成功")
    @GetMapping("/get/{date}")
    public LocalDateTimeDto getPerson(@PathVariable("date") LocalDate date, @RequestParam LocalDateTime localDateTime) {
        return new LocalDateTimeDto(date, localDateTime, LocalTime.now());
    }

    @SailResponse("查询成功")
    @GetMapping("/get")
    public LocalDateTimeDto getDto(LocalDateTimeDto dateTimeDto) {
        return dateTimeDto;
    }

    @SailResponse("保存成功")
    @PostMapping("/save")
    public LocalDateTimeDto getPerson(@RequestBody LocalDateTimeDto dateTimeDto) {
        return dateTimeDto;
    }

}
