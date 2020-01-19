package com.xidian.blog.controller;

import com.xidian.blog.constant.CodeType;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.service.UserService;
import com.xidian.blog.service.VerifyService;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.JsonResult;
import com.xidian.blog.utils.JsonUtils;
import com.xidian.blog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author 米
 * @date 2020/1/16
 */

@Controller
public class UserController {


    private UserService userService;

    private VerifyService verifyService;

    @Autowired
    public UserController(UserService userService,VerifyService verifyService){
        this.userService = userService;
        this.verifyService = verifyService;
    }

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
    /**
     *@Description: 用户信息注册
     *@Param: Map<String,Object> request 请求json数据
     *@Return: String json 返回json数据
     */
    @PostMapping("/common/register")
    @ResponseBody
    public String register(@RequestBody Map<String,Object> request){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(request.get("userName").toString());
        userEntity.setPassWord(request.get("passWord").toString());
        userEntity.setEmail(request.get("emailAddress").toString());

        System.out.println(userEntity.toString());

        boolean result = userService.register(userEntity);
        if(result){
            return ResponseUtil.send(CodeType.SUCCESS_STATUS).toJSON();
        }else{
            return ResponseUtil.send(CodeType.USER_IS_EXIST).toJSON();
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

    @PostMapping("/common/forgetPassWord")
    @ResponseBody
    public String forgetPassWord(@RequestBody Map<String , Object> request,HttpSession session){

        String code = request.get("verifyCode").toString();
        DataMap dataMap = userService.findUserByEmailAddress(request.get("emailAddress").toString());
        System.out.println(code);
        UserEntity userEntity = (UserEntity)dataMap.getData();
        System.out.println(userEntity);
        String userName = userEntity.getUserName();
        session.setAttribute("forgetUserName",userEntity.getUserName());
        DataMap dataMap1 = verifyService.checkVerify(userName,code);
        return JsonResult.build(dataMap1).toJSON();

    }

    @RequestMapping("/user/loginByEmail")
    @ResponseBody
    public String loginByEmail(@RequestBody Map<String,Object> request,HttpSession session){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassWord(request.get("passWord").toString());
        userEntity.setEmail(request.get("emailAddress").toString());
        String verifyCode = request.get("verifyCode").toString();
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            return ResponseUtil.send(CodeType.VERIFYCOODE_IS_ERROR).toJSON();
        }
        DataMap<UserEntity> result = userService.findUserByEmailAddress(userEntity.getEmail());
        if(result.getCode() == 0){
            UserEntity userEntity1 = result.getData();
            if(userEntity1.getPassWord().equals(userEntity.getPassWord())){
                session.setAttribute("loginUser",userEntity1.getUserName());
                return ResponseUtil.send(CodeType.SUCCESS_STATUS).toJSON();
            }else{
                return ResponseUtil.send(CodeType.PASSWORD_ERROR).toJSON();
            }

        }else {
            System.out.println("user null");
            return ResponseUtil.send(CodeType.USER_NULL).toJSON();
        }
    }




    @RequestMapping("/user/addFriend")
    String addFriend(@RequestParam("friendName") String friendName,HttpSession session){
        String masterUser = session.getAttribute("loginUser").toString();
        DataMap dataMap  = userService.findFriend(friendName,masterUser);
        if(dataMap.getCode()==1){
            return JsonResult.fail(CodeType.FRIEND_IS_EXIST.getMessage(),CodeType.FRIEND_IS_EXIST.getCode()).toJSON();
        }

        if(dataMap.getCode() == CodeType.USERNAME_IS_NULL.getCode()){
            return JsonResult.fail(CodeType.USERNAME_IS_NULL.getMessage(),CodeType.USERNAME_IS_NULL.getCode()).toJSON();
        }

        userService.addFriend(friendName,masterUser);

        return JsonResult.success().toJSON();
    }

    @RequestMapping("/user/index")
    @ResponseBody
    public String returnUserPage(HttpSession session){
        String userName = session.getAttribute("loginUser").toString();
        DataMap result = userService.findUserByUserName(userName);
        return JsonResult.build(result).toJSON();

    }
}
