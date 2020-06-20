package com.jun.cloud.restful.dto;

import com.jun.cloud.common.common.errorcode.DefaultErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


/**
 * @author 94977
 * @create 2019/1/1
 */
@Data
@ApiModel("方向对象")
public class DirectionDto {

    @NotBlank(message = DefaultErrorCode.ARGUMENTS_MISSING)
    @ApiModelProperty(notes = "方向唯一标识", example = "45587845465")
    private String id;

    @ApiModelProperty(notes = "开始时间", example = "2019-01-08")
    private LocalDate startDate;

    @ApiModelProperty(notes = "结束时间", example = "2019-01-14")
    private LocalDate endDate;

}
