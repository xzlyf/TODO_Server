package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.AppInfo;

/**
 * @Author: xz
 * @Date: 2020/12/6
 */
public interface AppInfoService {


    AppInfo findByAppid(String appid);


}
