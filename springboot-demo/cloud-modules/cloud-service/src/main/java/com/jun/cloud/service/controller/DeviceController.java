package com.jun.cloud.service.controller;


import com.jun.cloud.common.common.PageData;
import com.jun.cloud.service.dto.DeviceListDto;
import com.jun.cloud.service.dto.req.DeviceQueryDto;
import com.jun.cloud.service.service.IDeviceService;
import com.jun.sail.utils.commons.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 设备表，同步到本地 前端控制器
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备查询接口")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @GetMapping("/pageQuery")
    public BaseResponse<PageData<DeviceListDto>> queryDevicePage(@Validated DeviceQueryDto queryDto) {
        BaseResponse<PageData<DeviceListDto>> response = new BaseResponse<>();
        PageData<DeviceListDto> pageData = deviceService.queryConsume(queryDto);
        response.setData(pageData);
        Integer b = 2;
        List<DeviceQueryDto> arr = new ArrayList<>();
        while (b == 2) {
            arr.add(new DeviceQueryDto());
            System.out.println(arr.size());
        }
        return response;
    }

}

