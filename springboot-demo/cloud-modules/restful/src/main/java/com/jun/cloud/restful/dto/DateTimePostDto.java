package com.jun.cloud.restful.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Jun
 * 创建时间： 2019/5/11
 */
@Data
@ApiModel("测试时间Post对象")
public class DateTimePostDto {

    @ApiModelProperty(name = "nowDate", notes = "现在的日期", example = "2019-05-08")
    private LocalDate nowDate;

    @ApiModelProperty(name = "nowTime", notes = "现在的时间", example = "2019-05-08 05:05:45")
    private LocalDateTime nowTime;

    @ApiModelProperty(name = "name", notes = "名称", example = "余生君")
    @NotEmpty(message = "不能为空")
    private String name;


}
