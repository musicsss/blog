package com.xidian.blog.service.impl;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.xidian.blog.constant.CodeType;
import com.xidian.blog.dao.ArticleMapper;
import com.xidian.blog.entity.ArticleEntity;
import com.xidian.blog.service.ArticleService;
import com.xidian.blog.utils.DataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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
    public DataMap getArticleListByUserName(String userName, int limit,int articleStatus) {
        if(!(articleStatus == 0|| articleStatus==1)){
            return DataMap.fail(CodeType.CODE_ERROR);
        }
        List <ArticleEntity> articleEntityList = articleMapper.getArticleListByUserName(userName,limit,articleStatus);
        System.out.println(articleEntityList.isEmpty());
        DataMap dataMap = DataMap.success().setData(articleEntityList);
        System.out.println(dataMap.toString());
        return dataMap;
    }

    @Override
    public DataMap addArticle(ArticleEntity articleEntity) {
        articleEntity.setArticleCreateTime(new Date());
        int result = articleMapper.addArticle(articleEntity);
        System.out.println(result);
        if (result == 0){
            return  DataMap.fail(CodeType.ERROR_NOT_FOUND);
        }
        return DataMap.success(CodeType.SUCCESS_STATUS);
    }

    @Override
    public int findUserByArticleId(int articleId) {
        ArticleEntity articleEntity = articleMapper.getArticleEntityByArticleId(articleId);

        return 0;
    }

    @Override
    public ArticleEntity getArticleEntityByArticleId(int articleId) {
        return articleMapper.getArticleEntityByArticleId(articleId);
    }

    @Override
    public DataMap updateArticleStatus(int articleId, int articleStatus) {
        articleMapper.updateArticleStatus(articleStatus,articleId);
        return DataMap.success(CodeType.SUCCESS_STATUS);
    }

    @Override
    public List getArticleType() {
        System.out.println( articleMapper.getArticleType());
        return articleMapper.getArticleType();
    }
}
