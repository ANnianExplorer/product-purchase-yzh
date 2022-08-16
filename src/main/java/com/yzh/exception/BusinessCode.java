package com.yzh.exception;

/**
 * 业务代码
 *
 * @author 杨振华
 * @date 2022/08/15
 */
public enum BusinessCode {

    PARAM_ERROR("参数错误"),

    LOGIN_ERROR("账号或密码错误"),

    USER_MESSAGE_ERROR("此用户信息错误"),

    AUTH_ERROR("权限错误"),

    FILE_ERROR("文件异常"),

    ;

    private final String message;

    BusinessCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
