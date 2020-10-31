package com.jun.sail.springmvc.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Jun
 * 创建时间： 2019/5/12
 */
@Data
public class CountGroupDto {

    private String id;

    private String name = "测试统计组";

    private String type;

    private List<CameraDto> cameras;

    private String regionCode;

}
