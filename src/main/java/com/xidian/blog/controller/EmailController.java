package com.xidian.blog.controller;

import com.xidian.blog.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.xidian.blog.utils.VerCodeGenerateUtil.generateVerCode;

/**
 * @author 米
 * @date 2020/1/17
 */
@Controller
public class EmailController {
    @Autowired
    EmailService emailService;

    @GetMapping("/common/email")
    public String sendEmail(){
        return "email";
    }

    @PostMapping("/common/email")
    @ResponseBody
    public Object sendEmail(@RequestParam("emailAddress") String emailAddress) {
        try {
            emailService.sendEmailVerCode(emailAddress,generateVerCode());
            return "邮件发送成功";
        } catch (Exception e) {
            return "邮件发送失败";
        }
    }

}
