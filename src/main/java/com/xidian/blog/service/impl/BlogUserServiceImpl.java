package com.xidian.blog.service.impl;

import com.xidian.blog.dao.BlogUserMapper;
import com.xidian.blog.entity.BlogUserEntity;
import com.xidian.blog.service.BlogUserService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/29
 */
@Service
public class BlogUserServiceImpl implements BlogUserService {
    @Autowired
    BlogUserMapper blogUserMapper;

    @Override
    public PageResult getBlogUsersPage(PageQueryUtil pageUtil) {
        List<BlogUserEntity> blogUserEntityList = blogUserMapper.findBlogUserList(pageUtil);
        int total = blogUserMapper.getTotalBlogUsers(pageUtil);
        PageResult pageResult = new PageResult(blogUserEntityList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return blogUserMapper.lockUserBatch(ids, lockStatus) > 0;

    }
}
