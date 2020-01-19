package com.xidian.blog.dao;

import com.xidian.blog.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 米
 * @date 2020/1/20
 */
@Mapper
@Repository
public interface ArticleMapper {
    @Select("SELECT * FROM user_article WHERE articleMasterId IN ( SELECT userId FROM `user` " +
            "WHERE userName = #{userName}) ORDER BY articleCreateTime DESC LIMIT #{limit} ; ")
    List<ArticleEntity> getArticleListByUserName(String userName,int limit);
}
