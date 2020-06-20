package com.jun.cloud.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.cloud.common.common.PageData;
import com.jun.cloud.service.dto.DeviceListDto;
import com.jun.cloud.service.dto.req.DeviceQueryDto;
import com.jun.cloud.service.entity.Device;

/**
 * <p>
 * 设备表，同步到本地 服务类
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
public interface IDeviceService extends IService<Device> {

    PageData<DeviceListDto> queryConsume(DeviceQueryDto reqDto);

}
