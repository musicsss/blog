package com.xidian.blog.service;

import com.xidian.blog.entity.BlogTagCountEntity;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogTagService {
    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCountEntity> getBlogTagCountForIndex();
}
