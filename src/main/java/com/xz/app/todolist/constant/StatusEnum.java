package com.xz.app.todolist.constant;

public enum StatusEnum {
    SUCCESS(0, "success"),
    ERROR(-1, "未知错误"),
    ERROR_PARAMS(1, "参数错误"),
    ERROR_TOKEN(2, "token已过期"),
    ERROR_SIGN_NULL(3, "签名验证不存在"),
    ERROR_SIGN(4, "签名验证失败"),
    ERROR_TIMESTAMP_NULL(5, "时间戳不存在"),
    ERROR_TIMESTAMP(6, "时间未与服务器同步"),
    ERROR_TIMESTAMP_RECEIVE(7, "时间戳不合法"),
    ERROR_APPID_NULL(8, "APPID不存在"),
    NULL_USER(1045, "用户不存在"),
    FAILED_USER_ADD(1046,"用户创建失败"),
    FAILED_USER_UPDATE(1047,"用户更新失败"),
    FAILED_USER_EXIST(1048,"手机号已注册"),
    FAILED_USER_LOGIN(1049,"账号或密码错误"),
    FAILED_USER_LOGIN_NO_USER_PHONE(1050,"手机号未注册"),
    FAILED_USER_LOGIN_NO_USER_NO(1051,"账号未注册"),
    FAILED_USER_DETAIL_UPDATE(1052,"用户信息更新失败"),
    FAILED_EVENT_CREATE(1053,"事件创建失败"),
    FAILED_EVENT_DELETE(1054,"事件删除失败"),
    FAILED_EVENT_NULL(1055,"未找到对应事件");

    /**
     * 响应状态码
     */
    private final int code;
    /**
     * 响应提示
     */
    private final String msg;

    StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
