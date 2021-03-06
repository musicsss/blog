package com.xidian.blog.service;

import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.utils.DataMap;

/**
 * @author 米
 * @date 2020/1/16
 */
public interface UserService {
    /**
     * 用户传入账号密码，返回封装类型
     *@Description:用户登录验证
     *@Param:userName user name
     *@Param:passWord passWord原文
     *@Return: DataMap 返回封装类型
     *@Author:YL Wang
     *@Date:2020/1/16
     *@Time:16:58
     */
    DataMap login(String userName, String passWord);


    boolean register(UserEntity userEntity);

    void addFriend(String friendName, String masterUser);

    DataMap findFriend(String friendName, String masterUser);

    DataMap<UserEntity> findUserByEmailAddress(String emailAddress);

    DataMap findUserByUserName(String userName);

    DataMap findUserByUserId(int userId);
}
