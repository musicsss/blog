package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogGreetingEntity;

import com.xidian.blog.utils.PageQueryUtil;

import java.util.List;

/**
 * @author 米
 * @date 2020/4/3
 */
public interface BlogGreetingMapper {
    List<BlogGreetingEntity> findGreetingList(PageQueryUtil pageUtil);

    int getTotalGreeting(PageQueryUtil pageUtil);

    int insert(BlogGreetingEntity blogGreetingEntity);

    BlogGreetingEntity getGreetingByRow(int num);

    BlogGreetingEntity selectByPrimaryKey(int id);

    int update(BlogGreetingEntity blogGreetingEntity);

    int delete(int id);

    int deleteBatch(Integer[] ids);
}
