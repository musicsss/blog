package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogTagCountEntity;
import com.xidian.blog.entity.BlogTagEntity;
import com.xidian.blog.utils.PageQueryUtil;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogTagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(BlogTagEntity record);

    int insertSelective(BlogTagEntity record);

    BlogTagEntity selectByPrimaryKey(Integer tagId);

    BlogTagEntity selectByTagName(String tagName);

    int updateByPrimaryKeySelective(BlogTagEntity record);

    int updateByPrimaryKey(BlogTagEntity record);

    List<BlogTagEntity> findTagList(PageQueryUtil pageUtil);

    List<BlogTagCountEntity> getTagCount();

    int getTotalTags(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<BlogTagEntity> tagList);
}
