package com.jun.cloud.service.service;

import com.jun.cloud.common.common.PageData;
import com.jun.cloud.service.common.enums.RegionTypeEnum;
import com.jun.cloud.service.dto.RegionInfoDto;
import com.jun.cloud.service.dto.RegionQueryDto;
import com.jun.cloud.service.entity.Region;
import com.jun.sail.utils.code.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 测试实体属性相关
 * @author Jun
 * 创建时间： 2019/6/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RegionServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(RegionServiceTest.class);

    @Autowired
    private IRegionService regionService;


    /**
     * 测试枚举属性的插入，查询
     */
    @Test
    public void testRegionSave(){
        //String regionId = UuidUtil.getUuid();
        //Region region = createTempRegion()
        //        .setId(regionId)
        //        .setType(RegionTypeEnum.REGION_NORMAL);
        //regionService.save(region);
        //
        //Region regionSelect = regionService.getById(regionId);
        //logger.info("查询结果 :{}", regionSelect);
        //
        //regionService.removeById(regionId);
    }


    /**
     * 测试逻辑删除属性
     */
    @Test
    public void testRegionDel(){
        String regionId = UuidUtil.getUuid();
        Region region = createTempRegion()
                .setId(regionId);
        regionService.save(region);
        regionService.removeById(regionId);

        Region regionSelect = regionService.getById(regionId);
        Assert.isNull(regionSelect, "region删除失败");
    }


    /**
     * 测试主键类型为mybatis-plus来生成uuid
     */
    @Test
    public void testRegionIDType(){
        Region region = createTempRegion()
                .setId(null);

        logger.info("此时id为空:{}", region.getId());
        regionService.save(region);
        logger.info("保存操作后获得id:{}", region.getId());

        regionService.removeById(region.getId());
    }


    /**
     * 测试UpdateWrapper 必须有set
     */
    @Test
    public void testRegionUpdateWrapper(){
        //UpdateWrapper<Region> wrapper = new UpdateWrapper<>();
        ////修改的where条件
        //wrapper.eq("type", RegionTypeEnum.REGION_NORMAL);
        //
        ////要修改的值
        //wrapper.set("name", "修改名字");
        //
        ////以上等同于UPDATE region SET name='修改名字' WHERE delete_status=0 AND type = 1
        //regionService.update( wrapper);
    }


    /**
     * 测试自定义sql使用Wrapper
     */
    @Test
    public void testRegionCustomeWrapper(){
        //QueryWrapper<Region> queryWrapper = new QueryWrapper<Region>().eq("parent_code", "root000000");
        //
        //List<RegionInfoDto> regionInfoDtoList = regionService.selectRegionInfoList(queryWrapper);
        //logger.info("查询结果为：{}", regionInfoDtoList);
    }


    /**
     * 测试分页
     */
    @Test
    public void testRegionPageQuery(){
        RegionQueryDto queryDto = new RegionQueryDto();
        queryDto.setParentCode("root000000");

        PageData<RegionInfoDto> regionInfoDtoPageDto = regionService.selectRegionInfoByPage(queryDto);
        logger.info("查询结果为：{}", regionInfoDtoPageDto);
    }



    /**
     * 创建一个临时区域对象，获取后可以覆盖想要修改的属性
     */
    private Region createTempRegion(){
        //String regionCode = UuidUtil.getUuid();
        //
        //Region region = new Region()
        //        .setId(UuidUtil.getUuid())
        //        .setName("测试区域")
        //        .setPath("@root0000@" + regionCode + "@")
        //        .setLeaf(0)
        //        .setCode(regionCode)
        //        .setParentCode("root000000")
        //        .setType(RegionTypeEnum.REGION_NORMAL)
        //        .setCreateTime(LocalDateTime.now())
        //        .setUpdateTime(LocalDateTime.now())
        //        .setSort(1);
        //
        //return region;
        return null;
    }



}
