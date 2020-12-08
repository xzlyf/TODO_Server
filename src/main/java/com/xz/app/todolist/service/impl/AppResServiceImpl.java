package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.pojo.AppRes;
import com.xz.app.todolist.repository.AppResRepository;
import com.xz.app.todolist.service.AppResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xz
 * @Date: 2020/12/8
 */
@Service
public class AppResServiceImpl implements AppResService {
    @Autowired
    AppResRepository appResRepository;

    @Override
    public AppRes findByAppId(String appId) {
        return appResRepository.findByAppId(appId);
    }
}
