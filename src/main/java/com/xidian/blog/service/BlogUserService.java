package com.xidian.blog.service;

import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

/**
 * @author 米
 * @date 2020/3/29
 */
public interface BlogUserService {
    PageResult getBlogUsersPage(PageQueryUtil pageUtil);

    boolean lockUsers(Integer[] ids, int lockStatus);
}
