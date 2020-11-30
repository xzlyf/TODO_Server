package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.UserDetail;
import com.xz.app.todolist.pojo.vo.ApiResult;

/**
 * @Author: xz
 * @Date: 2020/11/30
 */
public interface DetailService {
    ApiResult updateDetail(String uuid, UserDetail detail);

    UserDetail saveDetail(String uuid, UserDetail detail);
}
