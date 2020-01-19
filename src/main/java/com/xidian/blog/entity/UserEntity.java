package com.xidian.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author 米
 * @date 2020/1/16
 */
public class UserEntity implements  Serializable {


    private long userId;

    private String userName;

    private String passWord;
    private String phone;
    private int gender;
    private String trueName;
    private String birthday;
    private String email;
    private String personalBrief;
    private String avatarImgUrl;
    private String address;
    private Byte isDeleted;
    private Byte lockedFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private List<RoleEntity> authorities;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWordMd5) {
        this.passWord = passWordMd5;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalBrief() {
        return personalBrief;
    }

    public void setPersonalBrief(String personalBrief) {
        this.personalBrief = personalBrief;
    }

    public String getAvatarImgUrl() {
        return avatarImgUrl;
    }

    public void setAvatarImgUrl(String avatarImgUrl) {
        this.avatarImgUrl = avatarImgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(Byte lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash= ").append(hashCode());
        stringBuilder.append(", userId=").append(userId);
        stringBuilder.append(", userName=").append(userName);
        stringBuilder.append(", passWord=").append(passWord);
        stringBuilder.append(", phone=").append(phone);
        stringBuilder.append(", gender=").append(gender);
        stringBuilder.append(", trueName=").append(trueName);
        stringBuilder.append(", birthday=").append(birthday);
        stringBuilder.append(", email=").append(email);
        stringBuilder.append(", personalBrief=").append(personalBrief);
        stringBuilder.append(", avatarImgUrl=").append(avatarImgUrl);
        stringBuilder.append(", address=").append(address);
        stringBuilder.append(", isDeleted=").append(isDeleted);
        stringBuilder.append(", lockedFlag=").append(lockedFlag);
        stringBuilder.append(", createTime=").append(createTime);
        stringBuilder.append("]");

        return stringBuilder.toString();


    }

}
