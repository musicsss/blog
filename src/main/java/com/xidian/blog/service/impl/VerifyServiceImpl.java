package com.xidian.blog.service.impl;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.dao.VerifyMapper;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.entity.VerifyEntity;
import com.xidian.blog.service.VerifyService;
import com.xidian.blog.utils.DataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 米
 * @date 2020/1/17
 */

@Service
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    VerifyMapper verifyMapper;
    @Override
    public DataMap createVerify(String userName,String code) {
        VerifyEntity verifyEntity = new VerifyEntity();
        Date now = new Date();
        verifyEntity.setCode(code);
        verifyEntity.setUserName(userName);
        verifyEntity.setDateCodeModified(now);
        verifyEntity.setDateEnterd(now);
        verifyEntity.setDateTimesModified(now);
        System.out.println(verifyEntity.toString());
        verifyMapper.createVerify(verifyEntity);
       return DataMap.success(CodeType.SUCCESS_STATUS);
    }

    @Override
    public DataMap checkVerify(String userName,String code){
        VerifyEntity verifyEntity = verifyMapper.findVerifyCodeByUserName(userName);
        if( null == verifyEntity){
            return DataMap.fail(CodeType.VERIFYCODE_NOT_SET);
        }
        Date now = new Date();
        if((verifyEntity.getDateEnterd().getTime()-now.getTime())>30000){
            return DataMap.fail(CodeType.VERIFYCODE_IS_EXPIRED);
        }
        if(verifyEntity.getCode().equals(code)){
            verifyEntity.setDateCodeModified(new Date());
            return DataMap.success(CodeType.SUCCESS_STATUS);
        }
        if(!verifyEntity.getCode().equals(code)){
            verifyEntity.setTimes(verifyEntity.getTimes()+1);
            verifyEntity.setDateCodeModified(new Date());
            return DataMap.fail(CodeType.VERIFYCOODE_IS_ERROR);
        }

        return DataMap.fail(CodeType.ERROR_NOT_FOUND);

    }

    @Override
    public boolean isAbleToCreateCode(String userName) {
        VerifyEntity verifyEntity = verifyMapper.findVerifyCodeByUserName(userName);

        if(null == verifyEntity){
            return true;
        }
        Date now = new Date();

        if((now.getTime()-verifyEntity.getDateEnterd().getTime())>300000){
            System.out.println(now.getTime()-verifyEntity.getDateEnterd().getTime());
            verifyMapper.deleteVerifyByUserName(userName);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCode(int code) {
        return false;
    }
}
