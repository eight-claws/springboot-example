package com.jun.cloud.service.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author Jun
 * 创建时间： 2019/7/7
 */
public interface SubTableMapper {

    void createSubTable(@Param("sql") String sql);

    String findBySql(@Param("sql") String sql);


}
