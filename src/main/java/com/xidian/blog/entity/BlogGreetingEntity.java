package com.xidian.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

/**
 * @author 米
 * @date 2020/4/3
 */
public class BlogGreetingEntity {

    private int id;

    private String content;

    private int greetingType;

    private int isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public int getGreetingType() {
        return greetingType;
    }

    public void setGreetingType(int greetingType) {
        this.greetingType = greetingType;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "BlogGreetingEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", greetingType=" + greetingType +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
