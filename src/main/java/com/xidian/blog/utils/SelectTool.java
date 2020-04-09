package com.xidian.blog.utils;

/**
 * @author 米
 * @date 2020/2/20
 */
public class SelectTool {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SelectTool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
