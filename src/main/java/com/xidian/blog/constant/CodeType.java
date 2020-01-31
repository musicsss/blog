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
    USER_NULL(6,"用户不存在"),
    PASSWORD_ERROR(1,"密码错误"),
    USERNAME_IS_NULL(2,"用户名为空"),
    PASSWORD_IS_NULL(3,"密码为空"),
    VERIFYCOODE_IS_NULL(4,"验证码为空"),
    NOT_FOUND(404,"该页面不存在"),
    VERIFYCOODE_IS_ERROR(5,"验证码错误"),
    FRIEND_IS_EXIST(6,"该用户已在好友列表中"),
    ERROR_NOT_FOUND(7,"未知错误，请联系管理员"),
    VERIFYCODE_NOT_SET(8,"用户未设置验证码或验证码已过期"),
    VERIFYCODE_IS_EXPIRED(9,"验证码已过期"),
    VERIFYCODE_IS_EXIST(10,"验证码已存在"),
    USER_IS_EXIST(11,"用户已存在"),
    CODE_ERROR(12,"代码指令错误"),
    NO_PERMISSION_TO_OPERATE(13,"无权进行操作"),
    REPEAT_OPERATE(14,"重复操作")
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
