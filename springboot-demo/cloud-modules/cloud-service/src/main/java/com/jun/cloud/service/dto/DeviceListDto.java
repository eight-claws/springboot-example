package com.jun.cloud.service.dto;

import lombok.Data;

/**
 * @author Jun
 * 创建时间： 2019/12/14
 */
@Data
public class DeviceListDto {

    /**
     * 主键，使用32位UUID
     */
    private String id;

    private String deviceName;

    private String regionCode;

    /**
     * 所属区域path
     */
    private String regionPath;

    private String treatyType;

    private String treatyTypeName;

    private String ip;

    private Integer port;


}
