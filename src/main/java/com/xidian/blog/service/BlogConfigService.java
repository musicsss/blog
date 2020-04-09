package com.xidian.blog.service;


import java.util.Map;

/**
 * @author 米
 * @date 2020/3/31
 */
public interface BlogConfigService {

    /**
     *@Description: 修改配置项
     *@Param: 配置名称
     *@Param: 配置值
     *@Return: 成功影响行数
     */
    int updateConfig(String configName, String configValue);
    /**
     *@Description:获取配置项
     *@Param:null
     *@Return: 配置项键值对集合
     */
    Map<String,String> getAllConfigs();
}
