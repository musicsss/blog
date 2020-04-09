package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogCategoryEntity;
import com.xidian.blog.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogCategoryMapper {
    /**
     *@Description: 通过主键删除分类
     *@Param: categoryId 分类主键
     *@Return: 删除影响行数
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     *@Description: 通过BlogCategoryEntity封装类新增分类
     *@Param: record BlogCategoryEntity封装类
     *@Return: 结果影响行数
     */
    int insert(BlogCategoryEntity record);

    /**
     *@Description: 通过BlogCategoryEntity封装类新增分类,该类属性为null则使用系统默认值
     *@Param: record BlogCategoryEntity封装类
     *@Return: 结果影响行数
     */
    int insertSelective(BlogCategoryEntity record);
    /**
     *@Description: 通过主键查询分类详情
     *@Param: categoryId 分类id
     *@Return: BlogCategoryEntity
     */
    BlogCategoryEntity selectByPrimaryKey(Integer categoryId);

    /**
     *@Description: 通过分类名获取分类详情
     *@Param: categoryName 分类名
     *@Return: BlogCategoryEntity
     */
    BlogCategoryEntity selectByCategoryName(String categoryName);

    int updateByPrimaryKeySelective(BlogCategoryEntity record);

    int updateByPrimaryKey(BlogCategoryEntity record);

    List<BlogCategoryEntity> findCategoryList(PageQueryUtil pageUtil);

    List<BlogCategoryEntity> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    int getTotalCategories(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
}
