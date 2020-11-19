package com.xz.app.todolist.utils;

public enum StatusEnum {
    SUCCESS(0, "success"),
    ERROR(-1, "未知错误"),
    NULL_USER(1045, "用户不存在"),
    FAILED_USER_ADD(1046,"用户创建失败");

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
