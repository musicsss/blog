package com.xidian.blog.dao;

import com.xidian.blog.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 米
 * @date 2020/1/16
 */

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where phone=#{phone}")
    @Results({
            @Result(column = "userName", property = "userName"),
            @Result(column = "passWord", property = "passWord"),
            @Result(column = "phone", property = "roles"),
    })
    UserEntity getUsernameAndRolesByPhone(@Param("phone") String phone);

    @Select("select * from user where userName=#{userName}")
    @Results({
            @Result(column = "userName", property = "userName"),
            @Result(column = "passWord", property = "passWord"),
    })
    UserEntity getUsernameAndRolesByUserName(@Param("userName") String userName);


    @Insert(",insert into user(phone,userName,passWord) values(#{phone},#{userName},#{passWord})")
    void save(UserEntity userEntity);
}
