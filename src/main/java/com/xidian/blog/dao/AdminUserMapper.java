package com.xidian.blog.dao;

import com.xidian.blog.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 米
 * @date 2020/3/24
 */

public interface AdminUserMapper {
    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    /**
     * 登陆方法
     *
     * @param userName
     * @param password
     * @return AdminUser
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}
