package com.xidian.blog.service;

import com.xidian.blog.entity.BlogLinkEntity;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogLinkService {
    /**
     * 查询友链的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    int getTotalLinks();

    Boolean saveLink(BlogLinkEntity link);

    BlogLinkEntity selectById(Integer id);

    Boolean updateLink(BlogLinkEntity tempLink);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回友链页面所需的所有数据
     *
     * @return
     */
    Map<Byte, List<BlogLinkEntity>> getLinksForLinkPage();
}
