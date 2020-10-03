package com.jun.cloud.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.cloud.common.common.PageData;
import com.jun.cloud.service.dto.RegionInfoDto;
import com.jun.cloud.service.dto.RegionQueryDto;
import com.jun.cloud.service.entity.Region;
import com.jun.cloud.service.mapper.RegionMapper;
import com.jun.cloud.service.service.IRegionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 余生君
 * @since 2019-06-12
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {


    /**
     * 在定义的sql中使用mybatis的wrapper条件
     */
    @Override
    public List<RegionInfoDto> selectRegionInfoList(@Param(Constants.WRAPPER) Wrapper wrapper) {
        return baseMapper.selectRegionInfos(wrapper);
    }

    /**
     * 使用mybatis的page进行分页查询
     * 不需要在自定义sql中写分页参数了
     */
    @Override
    public PageData<RegionInfoDto> selectRegionInfoByPage(RegionQueryDto queryDto) {
        Page<RegionQueryDto> page = new Page<>();
        page.setSize(10);
        page.setCurrent(1);
        //是否查询总条数
        page.setSearchCount(true);

        //分页返回的Page对象与传入的Page对象是同一个
        Page<RegionInfoDto> regionPage = baseMapper.selectRegionInfoByPage(page, queryDto);

        PageData<RegionInfoDto> pageDto = new PageData<>();
        pageDto.setPageNo((int) regionPage.getCurrent());
        pageDto.setPageSize((int) regionPage.getSize());
        pageDto.setTotal(regionPage.getTotal());
        pageDto.setList(regionPage.getRecords());

        return pageDto;
    }


}
