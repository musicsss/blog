package com.xidian.blog.controller;

import com.xidian.blog.service.ArticleService;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 米
 * @date 2020/1/20
 */
@RestController
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @RequestMapping("/article/getSimpleAticleList")
    public String getSimpleAticleList(@RequestBody Map<String,Object> request){
        String userName = request.get("userName").toString();
        int limit = (int)request.get("limit");

        DataMap dataMap = articleService.getArticleListByUserName(userName,limit);
        System.out.println(dataMap.getData().toString());
        return JsonResult.build(dataMap).toJSON();
    }
}
