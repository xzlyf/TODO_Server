package com.xz.app.todolist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResult {
    private int code;
    private String status;
    //如果没有数据传入，则过滤掉此输出
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

    //如果没有数据传入，则过滤掉此输出
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public ApiResult() {
    }

    public ApiResult(int code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public ApiResult(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ApiResult(int code, String status, Object data, String message) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
