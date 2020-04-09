package com.xidian.blog.service;

import com.xidian.blog.entity.BlogCategoryEntity;
import com.xidian.blog.entity.ItemsCategory;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/29
 */
public interface BlogCategoryService {
    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    int getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    List<BlogCategoryEntity> getAllCategories();
    List<ItemsCategory> selectByLevelAndParentIdsAndNumber(List<Long> singletonList, int level);
}
