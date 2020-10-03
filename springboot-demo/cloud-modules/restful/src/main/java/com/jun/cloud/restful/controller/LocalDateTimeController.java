package com.jun.cloud.restful.controller;

import com.jun.cloud.restful.dto.DateTimeGetDto;
import com.jun.cloud.restful.dto.DateTimePostDto;
import com.jun.sail.utils.commons.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 测试LocalDateTime的接收与返回
 *
 * @author Jun
 * 创建时间： 2019/5/11
 */

@RestController
@RequestMapping("/time")
@Api(tags = "日期时间测试")
@Validated
public class LocalDateTimeController {

    @PostMapping("/post")
    @ApiOperation(value = "测试post返回时间", notes = "返回当前时间，before返回一天后的时间")
    public BaseResponse testLocalDateTime(@Validated @RequestBody DateTimePostDto dateTimeDto) {
        BaseResponse<DateTimePostDto> response = new BaseResponse<>();

        DateTimePostDto dto = new DateTimePostDto();
        dto.setNowDate(LocalDate.now());
        dto.setNowTime(LocalDateTime.now());
        dto.setName(dateTimeDto.getName());

        response.setData(dto);
        return response;
    }


    @GetMapping("/get/{pathTime}")
    @ApiOperation(value = "测试get返回时间", notes = "返回当前时间，before返回一天后的时间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDateTime", value = "开始时间，返回一天后的时间", required = true, example = "2018-08-02 05:05:06"),
            @ApiImplicitParam(name = "pathTime", value = "作为path参数传入，返回一小时后的时间", required = true, example = "2020-08-02 05:05:06")
    })
    public BaseResponse testLocalDateTimeGet(@NotEmpty(message = "不能为空") @RequestParam LocalDateTime startDateTime, @PathVariable LocalTime pathTime) {
        BaseResponse<DateTimeGetDto> response = new BaseResponse<>();

        DateTimeGetDto dto = new DateTimeGetDto();
        dto.setPathTime(pathTime.plusHours(1));
        dto.setStartDateTime(startDateTime.plusDays(1));

        response.setData(dto);
        return response;
    }


}
