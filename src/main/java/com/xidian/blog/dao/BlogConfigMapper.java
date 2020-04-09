package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogConfigEntity;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogConfigMapper {
    List<BlogConfigEntity> selectAll();

    BlogConfigEntity selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfigEntity record);
}
