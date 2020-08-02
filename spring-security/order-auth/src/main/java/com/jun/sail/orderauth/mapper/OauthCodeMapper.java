package com.jun.sail.orderauth.mapper;

import com.jun.sail.orderauth.entity.OauthCodeEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;


@Mapper
public interface OauthCodeMapper {


    @Delete("DELETE FROM T_OAUTH_CODE WHERE ID = #{id}")
    int deleteById(long id);

    @Insert("INSERT INTO T_OAUTH_CODE(CODE, AUTHENTICATION) VALUES(#{code}, #{authentication})")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    int insert(OauthCodeEntity record);

    @Select("SELECT * FROM T_OAUTH_CODE WHERE CODE = #{code} AND CREATE_TIME >= #{validTime} limit 1")
    OauthCodeEntity findValidOneByCode(@Param("code") String code, @Param("validTime") LocalDateTime validTime);
}
