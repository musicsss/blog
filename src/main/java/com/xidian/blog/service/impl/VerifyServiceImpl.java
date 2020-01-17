package com.xidian.blog.service.impl;

import com.xidian.blog.dao.VerifyMapper;
import com.xidian.blog.entity.VerifyEntity;
import com.xidian.blog.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 米
 * @date 2020/1/17
 */

@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    VerifyMapper verifyMapper;
    @Override
    public void createVerify(VerifyEntity verifyEntity) {

        verifyMapper.createVerify(verifyEntity);
    }

    @Override
    public boolean isAbleToCreateCode(String userName) {

        verifyMapper.findVerifyCodeByUserName(userName);
        return false;
    }

    @Override
    public boolean checkCode(int code) {
        return false;
    }
}
