package com.xidian.blog.utils;

import com.xidian.blog.constant.CodeType;

/**
 * @author 米
 * @date 2020/1/18
 */
public class ResponseUtil {

    private int code;
    private String message;

    public static JsonResult.JsonData send(CodeType codeType){

        return JsonResult.success("status",codeType.getCode()).add("message",codeType.getMessage());
    }
}
