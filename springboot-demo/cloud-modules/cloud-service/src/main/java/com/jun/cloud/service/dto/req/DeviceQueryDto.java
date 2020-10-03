package com.jun.cloud.service.dto.req;

import com.jun.cloud.common.common.PageQueryDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author Jun
 * 创建时间： 2019/12/14
 */
@Data
@ApiModel(value = "设备查询请求对象")
public class DeviceQueryDto extends PageQueryDto {

    @ApiModelProperty(value = "设备名称", name = "deviceName")
    private String deviceName;

    @NotEmpty(message = "region.not.null")
    @ApiModelProperty(value = "设备所在区域code", name = "regionCode")
    private String regionCode;

    @ApiModelProperty(value = "设备协议", name = "treatyType")
    private String treatyType;


}
