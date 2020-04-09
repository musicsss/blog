package com.xidian.blog.VO;

import java.io.Serializable;

/**
 * @author 米
 * @date 2020/3/31
 */


/**
 *@Description: 后台博客数据简单封装
  *@Author:YL Wang
 *@Date:2020/3/31
 *@Time:18:02
 */
public class SimpleBlogListVO implements Serializable {
    private Long blogId;

    private String blogTitle;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
}
