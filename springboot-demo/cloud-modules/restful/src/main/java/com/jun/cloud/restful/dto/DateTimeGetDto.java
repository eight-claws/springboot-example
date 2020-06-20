package com.jun.cloud.restful.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Jun
 * 创建时间： 2019/5/11
 */
@Data
@ApiModel("测试时间Get对象")
public class DateTimeGetDto {

    @ApiModelProperty(name = "pathTime", notes = "路径上输入时间，返回一天后的时间",example = "12:00:45",required = true)
    private LocalTime pathTime;

    @ApiModelProperty(name = "startDateTime", notes = "开始日期，返回一天后的时间",example = "2019-05-08 06:05:12",required = true)
    private LocalDateTime startDateTime;

}
