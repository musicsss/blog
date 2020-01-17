package com.xidian.blog.service.impl;

import com.xidian.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author 米
 * @date 2020/1/17
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public void sendEmailVerCode(String receiver, String verCode) throws MailSendException {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("验证码");
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");	//设置邮件正文
        message.setTo(receiver);
        message.setFrom(sender);
        mailSender.send(message);
    }
}
