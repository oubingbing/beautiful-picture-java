package com.kucaroom.mypicture.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select count(id) from user where app_id = #{appId,jdbcType=INTEGER} and create_at between #{startDate,jdbcType=VARCHAR} and #{endDate,jdbcType=VARCHAR}")
    Integer countUserByAppIdAndDate(@Param("appId")Integer appId,@Param("startDate")String startDate,@Param("endDate")String endDate);

    @Select("select count(id) from user where app_id = #{appId,jdbcType=INTEGER}")
    Integer countUserByAppId(@Param("appId")Integer appId);
}
