package com.jun.cloud.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Jun
 * 创建时间： 2019/12/14
 */
@ApiModel(description = "分页数据返回内容")
@Data
public class PageData<T> {

    @ApiModelProperty(value = "当前页码")
    private Integer pageNo;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "列表数据")
    private List<T> list;

    @ApiModelProperty(value = "列表数据总数")
    private Long total;

}
