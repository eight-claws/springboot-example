package com.jun.cloud.service.service;

import com.jun.cloud.service.common.enums.RegionTypeEnum;
import com.jun.cloud.service.entity.Device;
import com.jun.cloud.service.entity.Region;
import com.jun.sail.utils.code.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jun
 * 创建时间： 2019/6/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class DeviceServiceTest {
    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IRegionService regionService;

    @Test
    public void testDeviceSave() {
        Device device = new Device();
        device.setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setName("测试设备")
                .setRegionCode("root0000");
        deviceService.removeById("sdsd");
        //deviceService.save(device);
    }

    @Test
    public void testRegionSave() {
        //Region region = new Region()
        //        .setId(UuidUtil.getUuid())
        //        .setCode(UuidUtil.getUuid())
        //        .setName("测试区域")
        //        .setPath("@root0000")
        //        .setType(RegionTypeEnum.REGION_NORMAL)
        //        .setCreateTime(LocalDateTime.now())
        //        .setUpdateTime(LocalDateTime.now());
        //regionService.save(region);
        //
        //List<Region> list = regionService.list();
        //System.out.println(list);
    }


}
