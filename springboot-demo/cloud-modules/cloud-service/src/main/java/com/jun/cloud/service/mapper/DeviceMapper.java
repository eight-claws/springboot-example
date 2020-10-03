package com.jun.cloud.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jun.cloud.service.dto.DeviceListDto;
import com.jun.cloud.service.dto.req.DeviceQueryDto;
import com.jun.cloud.service.entity.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设备表，同步到本地 Mapper 接口
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
public interface DeviceMapper extends BaseMapper<Device> {

    List<DeviceListDto> queryDevicePage(@Param("queryDto") DeviceQueryDto queryDto);

    Long countDevicePage(@Param("queryDto") DeviceQueryDto queryDto);

}
