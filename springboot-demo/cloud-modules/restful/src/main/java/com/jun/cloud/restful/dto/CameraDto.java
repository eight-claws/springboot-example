package com.jun.cloud.restful.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Jun
 * 创建时间： 2019/5/12
 */
@Data
@ApiModel("监控点对象")
public class CameraDto {

    @ApiModelProperty(name = "code", notes = "监控点code", example = "2342er578-7981-46ds223d3d-sds-d22")
    private String code;

    @ApiModelProperty(name = "direc", notes = "监控点方向,1-进，2-出", example = "2")
    private Integer direc;

    public CameraDto(String code, Integer direc) {
        this.code = code;
        this.direc = direc;
    }


}
