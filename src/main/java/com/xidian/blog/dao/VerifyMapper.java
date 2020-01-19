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
    @Select("select * from verify where userName = #{userName}")
    @Results({
            @Result(column = "code", property = "code"),
            @Result(column = "userName",property = "userName"),
            @Result(column = "times",property = "times"),
            @Result(column = "dateEnterd",property = "dateEnterd"),
            @Result(column = "dateCodeModified",property = "dateCodeModified"),
            @Result(column = "dateTimesModified",property = "dateTimesModified")
    })
    VerifyEntity findVerifyCodeByUserName(@Param("userName") String userName);

    @Select("select code from verify where times = #{times}")
    @Results({
            @Result(column = "times", property = "times"),
    })
    int findTimesByUserName(@Param("times") String times);

    @Insert("insert into verify (userName,code,dateEnterd,dateCodeModified,dateTimesModified) " +
            "values(#{userName},#{code},#{dateEnterd},#{dateCodeModified},#{dateTimesModified})")
    void createVerify(VerifyEntity verifyEntity);

    @Delete("delete from verify where userName = #{userName}")
    void deleteVerifyByUserName(String userName);
}
