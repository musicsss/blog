package com.xidian.blog.dao;

import com.xidian.blog.entity.VerifyEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author 米
 * @date 2020/1/17
 */

@Mapper
@Repository
public interface VerifyMapper {
    @Select("select code from verify where userName = {#userName}")
    @Results({
            @Result(column = "code", property = "code"),
    })
    int findVerifyCodeByUserName(@Param("userName") String userName);

    @Select("select code from verify where times = {#times}")
    @Results({
            @Result(column = "times", property = "times"),
    })
    int findTimesByUserName(@Param("times") String times);

    @Insert("insert into verify (userName,code,dateEnterd,dateCodeModified,dateTimesModified) " +
            "values(#{userName},#{code},#{dateEnterd},#{dateCodeModified},#{dateTimeModified}")
    void createVerify(VerifyEntity verifyEntity);
}
