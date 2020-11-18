package com.xz.app.todolist.dto;

public class ApiResult {
    private int code;
    private String status;
    private Object data;
    private String message;

    public ApiResult() {
    }

    public ApiResult(int code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
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
