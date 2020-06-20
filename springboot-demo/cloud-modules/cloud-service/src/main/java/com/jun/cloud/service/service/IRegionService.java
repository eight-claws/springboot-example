package com.jun.cloud.service.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.cloud.common.common.PageData;
import com.jun.cloud.service.dto.RegionInfoDto;
import com.jun.cloud.service.dto.RegionQueryDto;
import com.jun.cloud.service.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
public interface IRegionService extends IService<Region> {

    List<RegionInfoDto> selectRegionInfoList(@Param(Constants.WRAPPER) Wrapper wrapper);


    PageData<RegionInfoDto> selectRegionInfoByPage(RegionQueryDto queryDto);

}
