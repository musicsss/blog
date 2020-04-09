package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogUserEntity;
import com.xidian.blog.utils.PageQueryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/29
 */
@Repository
public interface BlogUserMapper {
    List<BlogUserEntity> findBlogUserList(PageQueryUtil pageUtil);

    int getTotalBlogUsers(PageQueryUtil pageUtil);

    int lockUserBatch(Integer[] ids, int lockStatus);
}
