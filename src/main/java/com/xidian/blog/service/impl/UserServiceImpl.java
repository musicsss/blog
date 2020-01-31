package com.xidian.blog.service.impl;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.dao.UserMapper;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.service.UserService;
import com.xidian.blog.utils.DataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 米
 * @date 2020/1/16
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public DataMap login(String userName, String passWord){

           UserEntity userEntity = userMapper.getUsernameAndRolesByUserName(userName);

           if(null == userEntity){
               return DataMap.fail(CodeType.USER_NULL);

           }

           if(!userEntity.getPassWord().equals(passWord)){
               return DataMap.fail(CodeType.PASSWORD_ERROR);
           }

           return DataMap.success(CodeType.SUCCESS_STATUS).setData(userEntity);

    }

    @Override
    public boolean register(UserEntity userEntity) {
        if(null != userMapper.getUsernameAndRolesByUserName(userEntity.getUserName())){
            return false;
        }

        userMapper.saveUser(userEntity);
        return true;
    }

    @Override
    public void addFriend(String friendName, String masterUser) {

    }

    @Override
    public DataMap findFriend(String friendName, String masterUser) {
        return null;
    }

    @Override
    public DataMap<UserEntity> findUserByEmailAddress(String emailAddress) {
        UserEntity userEntity = userMapper.findUserByEmailAddress(emailAddress);
        DataMap<UserEntity> dataMap = DataMap.success();
        dataMap.setCode(CodeType.SUCCESS_STATUS.getCode());
        dataMap.message(CodeType.SUCCESS_STATUS.getMessage());
        dataMap.setData(userEntity);
        System.out.println(userEntity.toString());
        return dataMap;
    }

    @Override
    public DataMap findUserByUserName(String userName) {
        UserEntity userEntity = userMapper.findUserByUserName(userName);
        if(null == userEntity){
            return DataMap.fail(CodeType.USER_NULL);
        }
        return DataMap.success(CodeType.SUCCESS_STATUS).setData(userEntity);
    }

    @Override
    public DataMap findUserByUserId(int userId) {

        return DataMap.success().setData(userMapper.findUserByUserId(userId));
    }
}
