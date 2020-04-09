package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogCommentMapper {
    int deleteByPrimaryKey(Long commentId);

    int insert(BlogCommentEntity record);

    int insertSelective(BlogCommentEntity record);

    BlogCommentEntity selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogCommentEntity record);

    int updateByPrimaryKey(BlogCommentEntity record);

    List<BlogCommentEntity> findBlogCommentList(Map map);

    int getTotalBlogComments(Map map);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
}
