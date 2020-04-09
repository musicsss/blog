package com.xidian.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 米
 * @date 2020/3/31
 */
public class BlogEntity {
    private Long blogId;

    private String blogTitle;

    private String blogSubUrl;

    private String blogCoverImage;

    private Integer blogCategoryId;

    private String blogCategoryName;

    private String blogTags;

    private Byte blogStatus;

    private Long blogViews;

    private Byte enableComment;

    private Byte isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;

    private String blogContent;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

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

    public String getBlogSubUrl() {
        return blogSubUrl;
    }

    public void setBlogSubUrl(String blogSubUrl) {
        this.blogSubUrl = blogSubUrl;
    }

    public String getBlogCoverImage() {
        return blogCoverImage;
    }

    public void setBlogCoverImage(String blogCoverImage) {
        this.blogCoverImage = blogCoverImage;
    }

    public Integer getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(Integer blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags;
    }

    public Byte getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(Byte blogStatus) {
        this.blogStatus = blogStatus;
    }

    public Long getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(Long blogViews) {
        this.blogViews = blogViews;
    }

    public Byte getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Byte enableComment) {
        this.enableComment = enableComment;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blogId=").append(blogId);
        sb.append(", blogTitle=").append(blogTitle);
        sb.append(", blogSubUrl=").append(blogSubUrl);
        sb.append(", blogCoverImage=").append(blogCoverImage);
        sb.append(", blogCategoryId=").append(blogCategoryId);
        sb.append(", blogCategoryName=").append(blogCategoryName);
        sb.append(", blogTags=").append(blogTags);
        sb.append(", blogStatus=").append(blogStatus);
        sb.append(", blogViews=").append(blogViews);
        sb.append(", enableComment=").append(enableComment);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", blogContent=").append(blogContent);
        sb.append("]");
        return sb.toString();
    }
}
