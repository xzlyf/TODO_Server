package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.pojo.AppInfo;
import com.xz.app.todolist.repository.AppInfoRepository;
import com.xz.app.todolist.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xz
 * @Date: 2020/12/6
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    AppInfoRepository appInfoRepository;

    @Override
    public AppInfo findByAppid(String appid) {
        return appInfoRepository.findByAppid(appid);
    }
}
