package com.jun.sail.orderauth.mapper;

import com.jun.sail.orderauth.entity.UserAccountEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserAccountMapper {

    @Select("SELECT * FROM T_USER_ACCOUNT WHERE EMAIL = #{email}")
    UserAccountEntity findOne(String email);
}
