package com.jun.cloud.common.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Jun
 * 创建时间： 2019/12/14
 */
@ApiModel(description = "分页数据返回内容")
public class PageData extends ListData{

    @ApiModelProperty(value = "当前页码")
    private Integer pageNo;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "总页数")
    private Long totalPage;

}
