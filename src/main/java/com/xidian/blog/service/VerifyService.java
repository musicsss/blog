package com.xidian.blog.service;

import com.xidian.blog.entity.VerifyEntity;

/**
 * @author 米
 * @date 2020/1/17
 */
public interface VerifyService {
    void createVerify(VerifyEntity verifyEntity);

    boolean isAbleToCreateCode(String userName);

    boolean checkCode(int code);
}
