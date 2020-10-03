package com.jun.cloud.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Jun
 * 创建时间： 2019/12/15
 */
@Data
@ApiModel(value = "分页查询对象")
public class PageQueryDto {

    @ApiModelProperty(value = "页数 ，默认为1")
    @Min(value = 1, message = "pageNo.min.value.illegal")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页码，1到1000的整数，默认为100")
    @Min(value = 1, message = "pageSize.min.value.illegal")
    @Max(value = 1000, message = "pageSize.max.value.illegal")
    private Integer pageSize = 100;

}
