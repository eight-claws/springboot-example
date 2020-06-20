package com.jun.cloud.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.cloud.common.common.PageData;
import com.jun.cloud.service.dto.DeviceListDto;
import com.jun.cloud.service.dto.req.DeviceQueryDto;
import com.jun.cloud.service.entity.Device;
import com.jun.cloud.service.mapper.DeviceMapper;
import com.jun.cloud.service.service.IDeviceService;
import com.jun.sail.utils.check.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 设备表，同步到本地 服务实现类
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {


    @Override
    public PageData<DeviceListDto> queryConsume(DeviceQueryDto reqDto) {
        Integer pageNo = reqDto.getPageNo();
        Integer pageSize = reqDto.getPageSize();
        PageData<DeviceListDto> pageData = new PageData<>();
        pageData.setPageNo(pageNo);
        pageData.setPageSize(pageSize);

        if (ObjectUtils.isNotEmpty(reqDto.getDeviceName())) {
            reqDto.setDeviceName(ObjectUtils.escapeLikeParam(reqDto.getDeviceName()));
        }
        if (ObjectUtils.isNotEmpty(reqDto.getRegionCode())) {
            reqDto.setRegionCode(ObjectUtils.escapeLikeParam(reqDto.getRegionCode()));
        }
        reqDto.setPageNo((pageNo - 1) * reqDto.getPageSize());

        Long total = baseMapper.countDevicePage(reqDto);
        pageData.setTotal(total);
        List<DeviceListDto> consumeRecord = new ArrayList<>();
        pageData.setList(consumeRecord);
        if (total != null && total > 0) {
            consumeRecord = baseMapper.queryDevicePage(reqDto);
            pageData.setList(consumeRecord);
        }
        return pageData;
    }
}
