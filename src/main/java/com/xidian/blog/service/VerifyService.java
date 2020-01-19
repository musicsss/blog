package com.xidian.blog.service;

import com.xidian.blog.entity.VerifyEntity;
import com.xidian.blog.utils.DataMap;

/**
 * @author 米
 * @date 2020/1/17
 */
public interface VerifyService {
    DataMap createVerify(String  userName,String code);

    DataMap checkVerify(String usrName,String code);

    boolean isAbleToCreateCode(String userName);

    boolean checkCode(int code);
}
