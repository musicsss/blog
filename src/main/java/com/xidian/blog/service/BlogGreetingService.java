package com.xidian.blog.service;

import com.xidian.blog.entity.BlogGreetingEntity;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

/**
 * @author 米
 * @date 2020/4/3
 */
public interface BlogGreetingService {
    PageResult getBlogGreetingPage(PageQueryUtil pageUtil);

    boolean saveGreeting(BlogGreetingEntity blogGreetingEntity);

    BlogGreetingEntity selectRandomGreeting();

    BlogGreetingEntity getBlogGreetingEntityById(int id);

    boolean update(BlogGreetingEntity blogGreetingEntity);

    boolean deleteBatch(Integer[] ids);
}
