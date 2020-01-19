package com.xidian.blog.controller;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.entity.VerifyEntity;
import com.xidian.blog.service.EmailService;
import com.xidian.blog.service.UserService;
import com.xidian.blog.service.VerifyService;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.JsonResult;
import com.xidian.blog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.xidian.blog.utils.VerCodeGenerateUtil.generateVerCode;

/**
 * @author 米
 * @date 2020/1/17
 */
@Controller
public class EmailController {


    private  EmailService emailService;
    private VerifyService verifyService;
    private UserService userService;
    @Autowired
    public EmailController(EmailService emailService,VerifyService verifyService,UserService userService){
        this.emailService = emailService;
        this.verifyService = verifyService;
        this.userService = userService;
    }









    @GetMapping("/common/email")
    public String sendEmail(){
        return "email";
    }

    @PostMapping("/common/email")
    @ResponseBody
    public Object sendEmail(@RequestBody Map<String , Object> emailAddress) {
        String email = emailAddress.get("emailAddress").toString();
        DataMap dataMap = emailService.isAUserByEmail(email);
        if(dataMap.getCode() != 0){
            return ResponseUtil.send(CodeType.USER_NULL);
        }
        try {
            UserEntity userEntity = (UserEntity)userService.findUserByEmailAddress(email).getData();
            if(!verifyService.isAbleToCreateCode(userEntity.getUserName())){
                return ResponseUtil.send(CodeType.VERIFYCODE_IS_EXIST);
            }

            String code = generateVerCode();
            verifyService.createVerify(userEntity.getUserName(),code);
            emailService.sendEmailVerCode(emailAddress.get("emailAddress").toString(),code);
            return ResponseUtil.send(CodeType.SUCCESS_STATUS);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace()+"");
            return ResponseUtil.send(CodeType.NOT_FOUND);
        }
    }

}
