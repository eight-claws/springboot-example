package com.jun.cloud.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.jun.cloud.service.common.enums.RegionTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，32位uuid
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域path
     */
    private String path;

    /**
     * 是否是叶子节点。0-叶子节点，1-非叶子节点
     */
    private Integer leaf;

    /**
     * 删除状态。1-删除，0未删除（默认值）
     */
    @TableLogic
    private Integer deleteStatus;

    /**
     * 区域code
     */
    private String code;

    /**
     * 父节点的code
     */
    private String parentCode;

    /**
     * 区域类型。1-普通，2-工地
     */
    private RegionTypeEnum type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private Integer sort;


}
