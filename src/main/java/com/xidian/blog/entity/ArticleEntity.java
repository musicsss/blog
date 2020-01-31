package com.xidian.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 米
 * @date 2020/1/20
 */
public class ArticleEntity {
    private int articleId;
    private int articleTypeId;
    private String articleContent;
    private String articleTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date articleCreateTime;
    private String articleInfo;
    private int articleReadTimes;
    private int articleMasterId;
    private int articleStatus;

    public int getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(int articleStatus) {
        this.articleStatus = articleStatus;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(int articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(String articleInfo) {
        this.articleInfo = articleInfo;
    }

    public int getArticleReadTimes() {
        return articleReadTimes;
    }

    public void setArticleReadTimes(int articleReadTimes) {
        this.articleReadTimes = articleReadTimes;
    }

    public int getArticleMasterId() {
        return articleMasterId;
    }

    public void setArticleMasterId(int articleMasterId) {
        this.articleMasterId = articleMasterId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash= ").append(hashCode());
        stringBuilder.append(", articleId=").append(articleId);
        stringBuilder.append(", articleTypeId=").append(articleTypeId);
        stringBuilder.append(", articleContent=").append(articleContent);
        stringBuilder.append(", articleTitle=").append(articleTitle);
        stringBuilder.append(", articleCreateTime=").append(articleCreateTime);
        stringBuilder.append(", articleInfo=").append(articleInfo);
        stringBuilder.append(", articleReadTimes=").append(articleReadTimes);
        stringBuilder.append(", articleMasterId=").append(articleMasterId);
        stringBuilder.append(", articleStatus=").append(articleStatus);
        stringBuilder.append("]");

        return stringBuilder.toString();


    }
}
