package com.xidian.blog.entity;

import java.util.Date;

/**
 * @author 米
 * @date 2020/1/17
 */
public class VerifyEntity {
    private int verifyId;

    private String userName;

    private int code;

    private  Date dateEnterd;

    private  int times;

    private Date dateCodeModified;

    private Date dateTimesModified;

    public int getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(int verifyId) {
        this.verifyId = verifyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDateEnterd() {
        return dateEnterd;
    }

    public void setDateEnterd(Date dateEnterd) {
        this.dateEnterd = dateEnterd;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Date getDateCodeModified() {
        return dateCodeModified;
    }

    public void setDateCodeModified(Date dateCodeModified) {
        this.dateCodeModified = dateCodeModified;
    }

    public Date getDateTimesModified() {
        return dateTimesModified;
    }

    public void setDateTimesModified(Date dateTimesModified) {
        this.dateTimesModified = dateTimesModified;
    }
}
