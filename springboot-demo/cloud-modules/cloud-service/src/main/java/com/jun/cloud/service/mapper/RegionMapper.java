package com.jun.cloud.service.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.cloud.service.dto.RegionInfoDto;
import com.jun.cloud.service.dto.RegionQueryDto;
import com.jun.cloud.service.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
public interface RegionMapper extends BaseMapper<Region> {

    List<RegionInfoDto> selectRegionInfos(@Param(Constants.WRAPPER) Wrapper wrapper);

    Page<RegionInfoDto> selectRegionInfoByPage(Page page, @Param("queryDto") RegionQueryDto queryDto);

}
