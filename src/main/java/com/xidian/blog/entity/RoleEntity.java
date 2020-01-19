package com.xidian.blog.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author 米
 * @date 2020/1/19
 */
public class RoleEntity implements GrantedAuthority {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
