package com.xidian.blog.controller;

import com.alibaba.fastjson.JSON;
import com.xidian.blog.constant.CodeType;
import com.xidian.blog.entity.ArticleEntity;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.service.ArticleService;
import com.xidian.blog.service.UserService;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.JsonResult;
import com.xidian.blog.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 米
 * @date 2020/1/20
 */
@RestController
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;

    @Autowired
    ArticleController(ArticleService articleService,UserService userService){
        this.articleService = articleService;
        this.userService = userService;
    }

    @RequestMapping("/article/getSimpleAticleList")
    public String getSimpleArticleList(@RequestBody Map<String,Object> request){
        String userName = request.get("userName").toString();
        int limit = (int)request.get("limit");
        int status = (int)request.get("articleStatus");
        DataMap dataMap = articleService.getArticleListByUserName(userName,limit,status);
        System.out.println(dataMap.getData().toString());
        return JsonResult.build(dataMap).toJSON();
    }

    @RequestMapping("/article/addArticle")
    public String addArticle(@RequestBody String request, HttpSession session){
        session.setAttribute("loginUser","root");
        String user = session.getAttribute("loginUser").toString();
        UserEntity userEntity = (UserEntity)userService.findUserByUserName(user).getData();
        int userId = (int)userEntity.getUserId();
        ArticleEntity articleEntity = JSON.parseObject(request,ArticleEntity.class);
        articleEntity.setArticleMasterId(userId);
        System.out.println(articleEntity.toString());
        DataMap result = articleService.addArticle(articleEntity);

        return JsonResult.build(result).toJSON();
    }

    @RequestMapping("/article/updateArticleStatus")
    public String updateArticleStatus(@RequestBody Map<String,Object> request,HttpSession session){
        session.setAttribute("loginUser","root");
        String userName = session.getAttribute("loginUser").toString();
        int articleStatus = (int)request.get("articleStatus");
        int articleId = (int)request.get("articleId");
        ArticleEntity articleEntity = articleService.getArticleEntityByArticleId(articleId);

        System.out.println(articleEntity.toString());
        System.out.println(userService.findUserByUserId(1));
        UserEntity userEntity = (UserEntity)userService.findUserByUserId(articleEntity.getArticleMasterId()).getData();

        System.out.println(userEntity.toString());
         if(userEntity.getUserName().equals(userName)){
             if(articleEntity.getArticleStatus() == articleStatus){
                 return ResponseUtil.send(CodeType.REPEAT_OPERATE).toJSON();
             }
             DataMap dataMap = articleService.updateArticleStatus(articleId,articleStatus);
             return JsonResult.build(dataMap).toJSON();
         }

        return ResponseUtil.send(CodeType.NO_PERMISSION_TO_OPERATE).toJSON();
    }

    /**
     *@Description:返回分类信息
     *@Param:session
     *@Return:分类信息条数
     */
    @RequestMapping("/article/getClassificationInformation")
    public String getClassificationInformation(HttpSession session){

        //TODO 添加分类操作
        return null;
    }
}
