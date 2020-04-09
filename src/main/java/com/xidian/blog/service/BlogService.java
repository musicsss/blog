package com.xidian.blog.service;

import com.xidian.blog.VO.BlogDetailVO;
import com.xidian.blog.VO.SimpleBlogListVO;
import com.xidian.blog.entity.BlogEntity;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogService {

    /**
     *@Description: 保存博客内容
     *@Param: blogEntity
     *@Return: 成功 ”success“ 失败
     */
    String saveBlog(BlogEntity blogEntity);

    /**
     *@Description: 获取博客页面
     *@Param: 页面查询封装类
     *@Return: 页面查询结果封装
     */
    PageResult getBlogPage(PageQueryUtil pageQueryUtil);

    /**
     *@Description: 获取指定页面数据
     *@Param: 第 pageNum 页
     *@Return: 页面封装类
     */
    PageResult getBlogsForIndexPage(int pageNum);

    /**
     *@Description: 首页侧边栏数据列表
     *@Param: 获取类型 0-表示点击最多 1表示最新发布
     *@Return: 返回博文的简单分装类
     */
    List<SimpleBlogListVO> getBlogListForIndexPage(int type);

    /**
     *@Description: 按Id数组删除删除博客
     *@Param: 博客id数组
     *@Return: true false
     */
    Boolean deleteBatch(Integer[] ids);
    
    /**
     *@Description: 获取博客总数
     *@Param: null
     *@Return: 博客总数整形
     */
    int getTotalBlogs();

    /**
     *@Description: 根据id获取博客详情
     *@Param: Long 博客id
     *@Return: 博客实体类 BlogEntity
     */
    BlogEntity getBlogEntityById(Long blogId);

    /**
     *@Description: 后裔修改博客
     *@Param: 博客详情
     *@Return: 成功 失败原因
     */
    String updateBlog(BlogEntity blogEntity);

    /**
     *@Description: 获取博文详情
     *@Param: 博客id
     *@Return: 博客文章封装类 BlogDetailVO
     */
    BlogDetailVO getBlogDetail(Long blogId);

    /**
     *@Description: 根据标签获取文章列表
     *@Param: tagName 标签名 page 显示数目
     *@Return: 页面结果封装类
     */
    PageResult getBlogsPageByTag(String tagName, int page);

    /**
     *@Description: 根据分类显示页面
     *@Param: categoryName 分类名
     *@Param: page 页面显示数
     *@Return: PageResult 页面封装类
     */
    PageResult getBlogsPageByCategory(String categoryName,int page);
    
    /**
     *@Description: 根据搜索结果显示博文列表
     *@Param: keyword 搜索关键字
     *@Param: page 页面数目
     *@Return: PageResult 页面封装类
     */
    PageResult getBlogsPageBySearch(String keyword ,int page);

    /**
     *@Description: 通过博客自定义路径获取博客
     *@Param: subUrl 自定义博文路径
     *@Return: BlogDetailVO
     */
    BlogDetailVO getBlogDetailBySubUrl(String subUrl);

}
