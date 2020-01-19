package com.xidian.blog.service.impl;

import com.xidian.blog.dao.ArticleMapper;
import com.xidian.blog.entity.ArticleEntity;
import com.xidian.blog.service.ArticleService;
import com.xidian.blog.utils.DataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 米
 * @date 2020/1/20
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;

    @Autowired
    ArticleServiceImpl(ArticleMapper articleMapper ){
        this.articleMapper = articleMapper;
    }
    @Override
    public DataMap getArticleListByUserName(String userName, int limit) {
        List <ArticleEntity> articleEntityList = articleMapper.getArticleListByUserName(userName,limit);
        return DataMap.success().setData(articleEntityList);
    }
}
