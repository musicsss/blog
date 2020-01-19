package com.xidian.blog.service;

import com.xidian.blog.utils.DataMap;

/**
 * @author 米
 * @date 2020/1/17
 */
public interface EmailService {
    public void sendEmailVerCode(String receiver,String verCode);

    DataMap isAUserByEmail(String emailAddress);
}
