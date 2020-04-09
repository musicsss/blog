package com.xidian.blog.service;

import com.xidian.blog.entity.ArticleEntity;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.SelectTool;

import java.util.HashMap;
import java.util.List;

/**
 * @author 米
 * @date 2020/1/20
 */
public interface ArticleService {
    DataMap getArticleListByUserName(String userName, int limit,int articleStatus);

    DataMap addArticle(ArticleEntity articleEntity);

    int findUserByArticleId(int articleId);

    ArticleEntity getArticleEntityByArticleId(int articleId);

    DataMap updateArticleStatus(int articleId, int articleStatus);

    List<SelectTool> getArticleType();
}
