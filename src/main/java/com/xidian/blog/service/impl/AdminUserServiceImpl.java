package com.xidian.blog.service.impl;

import com.xidian.blog.dao.AdminUserMapper;
import com.xidian.blog.dao.UserMapper;
import com.xidian.blog.entity.AdminUser;
import com.xidian.blog.entity.UserEntity;
import com.xidian.blog.service.AdminUserService;
import com.xidian.blog.utils.MD5Util;
import com.xidian.blog.utils.PageQueryUtil;
import com.xidian.blog.utils.PageResult;
import com.xidian.blog.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 米
 * @date 2020/3/24
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password,"UTF-8");

        return adminUserMapper.login(userName,passwordMd5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {

        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新名称并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public PageResult getUserPage(PageUtil pageUtil) {
        List<UserEntity> users = userMapper.findUsers(pageUtil);
        int total = userMapper.getTotalUser(pageUtil);

        PageResult pageResult = new PageResult(users,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public PageResult getCarouselPage(PageQueryUtil pageUtil) {
        return null;
    }
}
