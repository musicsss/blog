package com.xidian.blog.service.impl;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.dao.UserMapper;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.service.UserService;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.JsonUtils;
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

        userMapper.save(userEntity);
        return true;
    }
}
