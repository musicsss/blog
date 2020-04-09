package com.xidian.blog.service;

import com.xidian.blog.entity.AdminUser;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageUtil;
import com.xidian.blog.utils.PageResult;

/**
 * @author 米
 * @date 2020/3/24
 */
public interface AdminUserService {
    AdminUser login(String userName, String password);

    /**
     * 获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 修改当前登录用户的密码
     *
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    /**
     * 修改当前登录用户的名称信息
     *
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);

    /**
     *@Description: 获取用户列表分页
     *@Param: 分页数据
     *@Return: 分页结果
     */
    public PageResult getUserPage(PageUtil pageUtil);

    public PageResult getCarouselPage(PageQueryUtil pageUtil);
}
