package com.xidian.blog.service;

/**
 * @author 米
 * @date 2020/1/17
 */
public interface EmailService {
    public void sendEmailVerCode(String receiver,String verCode);
}
