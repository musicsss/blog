package com.xidian.blog.service.impl;

import com.xidian.blog.dao.BlogTagMapper;
import com.xidian.blog.dao.BlogTagRelationMapper;
import com.xidian.blog.entity.BlogTagCountEntity;
import com.xidian.blog.entity.BlogTagEntity;
import com.xidian.blog.service.BlogTagService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    BlogTagMapper blogTagMapper;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<BlogTagEntity> tags = blogTagMapper.findTagList(pageUtil);
        int total = blogTagMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalTags() {
        return blogTagMapper.getTotalTags(null);
    }

    @Override
    public Boolean saveTag(String tagName) {
        BlogTagEntity temp = blogTagMapper.selectByTagName(tagName);
        if (temp == null) {
            BlogTagEntity blogTag = new BlogTagEntity();
            blogTag.setTagName(tagName);
            return blogTagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        //已存在关联关系不删除
        List<Long> relations = blogTagRelationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return blogTagMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogTagCountEntity> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }
}
