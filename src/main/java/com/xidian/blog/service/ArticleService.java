package com.xidian.blog.service;

import com.xidian.blog.entity.ArticleEntity;
import com.xidian.blog.utils.DataMap;

import java.util.List;

/**
 * @author 米
 * @date 2020/1/20
 */
public interface ArticleService {
    DataMap getArticleListByUserName(String userName, int limit);
}
