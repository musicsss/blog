package com.xidian.blog.dao;

import com.xidian.blog.entity.ArticleEntity;
import com.xidian.blog.utils.DataMap;
import com.xidian.blog.utils.SelectTool;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author 米
 * @date 2020/1/20
 */
@Mapper
@Repository
public interface ArticleMapper {
    @Select("SELECT * FROM user_article WHERE articleMasterId IN ( SELECT userId FROM `user` " +
            "WHERE userName = #{userName}) and articleStatus = #{articleStatus} ORDER BY articleCreateTime DESC LIMIT #{limit} ; ")
    List<ArticleEntity> getArticleListByUserName(String userName,int limit,int articleStatus);

    @SelectKey(statement = "select last_insert_id()" ,keyProperty = "articleId",keyColumn = "articleId",resultType = int.class,before = false)
    @Insert("INSERT INTO user_article(articleTypeId,articleContent,articleTitle,articleCreateTime,articleInfo,articleMasterId)" +
            "values(#{articleTypeId},#{articleContent},#{articleTitle},#{articleCreateTime},#{articleInfo},#{articleMasterId})")
    int  addArticle(ArticleEntity articleEntity);

    @Update("update user_article set articleStatus = #{articleStatus} where articleId = #{articleId}")
    void updateArticleStatus(@Param("articleStatus") int articleStatus,@Param("articleId") int articleId);

    @Select("select * from user_article where articleId = #{articleId}")
    ArticleEntity getArticleEntityByArticleId(int articleId);

    @Select("select articleTypeName from user_article_type where articleTypeId = #{articleTypeId}")
    String getArticleTypeNameByArticleTypeId(int articleTypeId);

    @Select("select articleTypeId,articleTypeName from user_article_type ")
    List<SelectTool> getArticleType();
}
