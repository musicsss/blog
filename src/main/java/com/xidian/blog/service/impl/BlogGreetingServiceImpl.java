package com.xidian.blog.service.impl;

import com.xidian.blog.dao.BlogGreetingMapper;
import com.xidian.blog.entity.BlogGreetingEntity;
import com.xidian.blog.entity.BlogLinkEntity;
import com.xidian.blog.service.BlogGreetingService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import com.xidian.blog.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author 米
 * @date 2020/4/3
 */
@Service
public class BlogGreetingServiceImpl implements BlogGreetingService {
    @Autowired
    BlogGreetingMapper blogGreetingMapper;
    @Override
    public PageResult getBlogGreetingPage(PageQueryUtil pageUtil) {
        List<BlogGreetingEntity> blogGreetingEntityList = blogGreetingMapper.findGreetingList(pageUtil);
        int total = blogGreetingMapper.getTotalGreeting(pageUtil);
        PageResult pageResult = new PageResult(blogGreetingEntityList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean saveGreeting(BlogGreetingEntity blogGreetingEntity) {
        return blogGreetingMapper.insert(blogGreetingEntity) >0;
    }

    @Override
    public BlogGreetingEntity selectRandomGreeting() {
        int total = blogGreetingMapper.getTotalGreeting(null);
        int num = RandomUtil.returnRandomNum(0,total-1);
        System.err.println("total:"+total+"---random num"+num);
        BlogGreetingEntity blogGreetingEntity = blogGreetingMapper.getGreetingByRow(num);
        return blogGreetingEntity;
    }

    @Override
    public BlogGreetingEntity getBlogGreetingEntityById(int id) {

        return blogGreetingMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean update(BlogGreetingEntity blogGreetingEntity) {
        System.out.println(blogGreetingEntity.toString());
        return blogGreetingMapper.update(blogGreetingEntity)>0;
    }

    @Override
    public boolean deleteBatch(Integer[] ids) {
        return blogGreetingMapper.deleteBatch(ids) > 0;
    }
}
