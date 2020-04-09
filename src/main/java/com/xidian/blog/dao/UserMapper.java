package com.xidian.blog.dao;

import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.PageUtil;
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


    @Insert("insert into user(userName,passWord,emailAddress)" +
            "values(#{userName},#{passWord},#{email})")
    void saveUser(UserEntity userEntity);

    @Select("select * from user where emailAddress = #{emailAddress}")
    @Results({
            @Result(column = "userName",property = "userName"),
            @Result(column = "passWord",property = "passWord"),
    })
    /**
     * 输入用户邮件地址，返回该用户实体类
     * @Param emailAddress email address
     * @Return com.xidian.blog.entity.UserEntity 用户实体类
     */
    UserEntity findUserByEmailAddress(String emailAddress);

    @Select("select * from user where userName = #{userName}")
    UserEntity findUserByUserName(String userName);

    @Select("select * from user where userId = #{userId}")
    UserEntity findUserByUserId(int userId);

    List<UserEntity> findUsers(PageUtil pageUtil);

    int getTotalUser(PageUtil pageUtil);
    


}
