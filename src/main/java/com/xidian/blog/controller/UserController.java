package com.xidian.blog.controller;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.service.UserService;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 米
 * @date 2020/1/16
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(HttpSession httpSession){
        System.out.println(httpSession.getAttribute("loginUser"));
        return "/index";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/common/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/common/register")
    public String register(@RequestParam("userName") String userName,
                           @RequestParam("passWord") String passWord,
                           @RequestParam("phone") String phone){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassWord(passWord);
        userEntity.setPhone(phone);


        System.out.println(userEntity.toString());
        boolean result = userService.register(userEntity);
        if(result){
            return "/login";
        }else{
            return "/error";
        }



    }


    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord,
                         @RequestParam("verifyCode") String verifyCode,
                        HttpSession httpSession,
                        HttpServletResponse response){
        if (StringUtils.isEmpty(verifyCode)) {
            httpSession.setAttribute("errorMsg", "验证码不能为空");
            return JsonResult.fail(CodeType.VERIFYCOODE_IS_NULL.getMessage(),003).toJSON();
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            httpSession.setAttribute("errorMsg", "用户名或密码不能为空");
            return JsonResult.fail(CodeType.USERNAME_IS_NULL.getMessage(),CodeType.USERNAME_IS_NULL.getCode()).toJSON();
        }
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            httpSession.setAttribute("errorMsg", "验证码错误");
            return JsonResult.fail(CodeType.VERIFYCOODE_IS_ERROR.getMessage(),CodeType.VERIFYCOODE_IS_ERROR.getCode()).toJSON();
        }
        DataMap dataMap = userService.login(userName, passWord);
        System.out.println(dataMap.getCode());
        if(dataMap.getCode().equals(001)){
            return JsonResult.fail(CodeType.PASSWORD_ERROR.getMessage(),001).toJSON();
        }
        if (dataMap.getCode().equals(0)) {
            httpSession.setAttribute("loginUser",userName);
            try{
                response.sendRedirect("/index");
            }catch (IOException ioe){
                return JsonResult.fail(CodeType.NOT_FOUND.getMessage(),404).toJSON();

            }
            return JsonResult.build(dataMap).toJSON();
        } else {

            return JsonResult.fail().toJSON();
        }
    }


    @GetMapping("/common/forgetPassword")
    String forgetPassWord(){
        return "forgetPassWord";
    }
}
