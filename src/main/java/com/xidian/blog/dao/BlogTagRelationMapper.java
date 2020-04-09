package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogTagRelationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogTagRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(BlogTagRelationEntity record);

    int insertSelective(BlogTagRelationEntity record);

    BlogTagRelationEntity selectByPrimaryKey(Long relationId);

    BlogTagRelationEntity selectByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Integer tagId);

    List<Long> selectDistinctTagIds(Integer[] tagIds);

    int updateByPrimaryKeySelective(BlogTagRelationEntity record);

    int updateByPrimaryKey(BlogTagRelationEntity record);

    int batchInsert(@Param("relationList") List<BlogTagRelationEntity> blogTagRelationList);

    int deleteByBlogId(Long blogId);
}
