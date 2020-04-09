package com.xidian.blog.dao;

import com.xidian.blog.entity.BlogEntity;
import com.xidian.blog.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogMapper {
    int deleteByPrimaryKey(Long blogId);

    int insert(BlogEntity record);

    int insertSelective(BlogEntity record);

    BlogEntity selectByPrimaryKey(Long blogId);

    int updateByPrimaryKeySelective(BlogEntity record);

    int updateByPrimaryKeyWithBLOBs(BlogEntity record);

    int updateByPrimaryKey(BlogEntity record);

    List<BlogEntity> findBlogList(PageQueryUtil pageUtil);

    List<BlogEntity> findBlogListByType(@Param("type") int type, @Param("limit") int limit);

    int getTotalBlogs(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    List<BlogEntity> getBlogsPageByTagId(PageQueryUtil pageUtil);

    int getTotalBlogsByTagId(PageQueryUtil pageUtil);

    BlogEntity selectBySubUrl(String subUrl);

    int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);

}
