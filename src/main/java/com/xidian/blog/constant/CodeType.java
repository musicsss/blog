package com.xidian.blog.constant;

/**
 * @author 米
 * @date 2020/1/16
 */
public enum CodeType {

    /**
     *
     * 状态码
     */
    SUCCESS_STATUS(0, "成功"),
    PHONE_EXIST(903, "手机号存在"),
    USER_NULL(006,"用户不存在"),
    PASSWORD_ERROR(001,"密码错误"),
    USERNAME_IS_NULL(002,"用户名为空"),
    PASSWORD_IS_NULL(003,"密码为空"),
    VERIFYCOODE_IS_NULL(004,"验证码为空"),
    NOT_FOUND(404,"该页面不存在"),
    VERIFYCOODE_IS_ERROR(005,"验证码错误"),
    ;

    private int code;

    private String message;

    CodeType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
