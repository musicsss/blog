package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogLinkEntity;
import com.xidian.blog.utils.PageQueryUtil;

import java.util.List;

/**
 * @author 米
 * @date 2020/4/2
 */
public interface BlogLinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(BlogLinkEntity record);

    int insertSelective(BlogLinkEntity record);

    BlogLinkEntity selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(BlogLinkEntity record);

    int updateByPrimaryKey(BlogLinkEntity record);

    List<BlogLinkEntity> findLinkList(PageQueryUtil pageUtil);

    int getTotalLinks(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
}
