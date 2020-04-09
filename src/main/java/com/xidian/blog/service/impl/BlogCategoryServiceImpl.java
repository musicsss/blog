package com.xidian.blog.service.impl;

import com.xidian.blog.dao.BlogCategoryMapper;
import com.xidian.blog.dao.BlogMapper;
import com.xidian.blog.entity.BlogCategoryEntity;
import com.xidian.blog.entity.ItemsCategory;
import com.xidian.blog.service.BlogCategoryService;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 米
 * @date 2020/3/30
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<BlogCategoryEntity> blogCategoryEntityList = blogCategoryMapper.findCategoryList(pageUtil);
        int total = blogCategoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(blogCategoryEntityList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategoryEntity temp = blogCategoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            BlogCategoryEntity blogCategoryEntity = new BlogCategoryEntity();
            blogCategoryEntity.setCategoryName(categoryName);
            blogCategoryEntity.setCategoryIcon(categoryIcon);
            blogCategoryEntity.setCreateTime(new Date());
            return blogCategoryMapper.insertSelective(blogCategoryEntity) > 0;
        }
        return false;    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategoryEntity blogCategoryEntity = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if (blogCategoryEntity != null) {
            blogCategoryEntity.setCategoryIcon(categoryIcon);
            blogCategoryEntity.setCategoryName(categoryName);
            //修改分类实体
            blogMapper.updateBlogCategorys(categoryName, blogCategoryEntity.getCategoryId(), new Integer[]{categoryId});
            return blogCategoryMapper.updateByPrimaryKeySelective(blogCategoryEntity) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        blogMapper.updateBlogCategorys("默认分类", 0, ids);
        //删除分类数据
        return blogCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogCategoryEntity> getAllCategories() {
        return  blogCategoryMapper.findCategoryList(null);
    }

    @Override
    public List<ItemsCategory> selectByLevelAndParentIdsAndNumber(List<Long> singletonList, int level) {
        return null;
    }
}
