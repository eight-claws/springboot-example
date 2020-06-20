package com.jun.cloud.service.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备表，同步到本地
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Device implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键，使用32位UUID
     */
    private String id;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 所属区域code
     */
    private String regionCode;

    /**
     * 所属区域path
     */
    private String regionPath;

    /**
     * 设备接入协议
     */
    private String treatyType;

    /**
     * 设备ip
     */
    private String ip;

    /**
     * 设备端口号
     */
    private Integer port;

    /**
     * 删除状态。1-删除，0-未删除（默认值）
     */
    private Integer deleteStatus;

    /**
     * 创建时间，不带时区
     */
    private LocalDateTime createTime;

    /**
     * 更新时间，不带时区
     */
    private LocalDateTime updateTime;


}
