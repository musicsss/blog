package com.xidian.blog.service.impl;

import com.xidian.blog.dao.BlogLinkMapper;
import com.xidian.blog.entity.BlogLinkEntity;
import com.xidian.blog.service.BlogLinkService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 米
 * @date 2020/4/1
 */
@Service
public class BlogLinkServiceImpl implements BlogLinkService {

    @Autowired
    private BlogLinkMapper blogLinkMapper;
    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<BlogLinkEntity> links = blogLinkMapper.findLinkList(pageUtil);
        int total = blogLinkMapper.getTotalLinks(pageUtil);
        PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalLinks() {
        return blogLinkMapper.getTotalLinks(null);
    }

    @Override
    public Boolean saveLink(BlogLinkEntity link) {
        link.setCreateTime(new Date());
        return blogLinkMapper.insertSelective(link)>0;
    }

    @Override
    public BlogLinkEntity selectById(Integer id) {
        return blogLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateLink(BlogLinkEntity tempLink) {
        return blogLinkMapper.updateByPrimaryKeySelective(tempLink) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogLinkMapper.deleteBatch(ids) > 0;
    }

    @Override
    public Map<Byte, List<BlogLinkEntity>> getLinksForLinkPage() {
        //获取所有链接数据
        List<BlogLinkEntity> links = blogLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(links)) {
            //根据type进行分组
            Map<Byte, List<BlogLinkEntity>> linksMap = links.stream().collect(Collectors.groupingBy(BlogLinkEntity::getLinkType));
            return linksMap;
        }
        return null;
    }
}
