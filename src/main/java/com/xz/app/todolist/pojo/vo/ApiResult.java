package com.xz.app.todolist.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xz.app.todolist.utils.StatusEnum;

/**
 * 通用实体数据返回
 */
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


    public ApiResult(StatusEnum status, Object data) {
        this.code = status.getCode();
        this.status = status.getMsg();
        this.data = data;
    }


    public ApiResult(StatusEnum status, Object data, String message) {
        this.code = status.getCode();
        this.status = status.getMsg();
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
