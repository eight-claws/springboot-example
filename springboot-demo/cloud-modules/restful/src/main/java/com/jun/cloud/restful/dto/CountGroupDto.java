package com.jun.cloud.restful.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Jun
 * 创建时间： 2019/5/12
 */
@Data
@ApiModel("统计组对象")
public class CountGroupDto {

    @ApiModelProperty(name = "id", notes = "统计组唯一id", example = "46578-7981-46ds2ad8d-sds-ddd")
    private String id;

    @ApiModelProperty(name = "name", notes = "统计组名称，不能包含特殊字符，最大20个字符", example = "一楼大门口")
    private String name = "测试统计组";

    @ApiModelProperty(name = "type", notes = "统计组类型，只能为1,2,3", example = "2")
    private String type;

    @ApiModelProperty(name = "cameras", notes = "统计组包含监控点列表，最多12个")
    private List<CameraDto> cameras;

    @ApiModelProperty(name = "regionCode", notes = "统计组所属区域", example = "we8-79121-46132ad8d-sds-ddd")
    private String regionCode;

}
