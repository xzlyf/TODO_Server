package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.AppRes;

/**
 * @Author: xz
 * @Date: 2020/12/8
 */
public interface AppResService {
    AppRes findByDownloadKey(String downloadKey);

    AppRes findByAppId(String appId);
}
