package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.UserDetail;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.repository.UserDetailRepository;
import com.xz.app.todolist.service.DetailService;
import com.xz.app.todolist.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: xz
 * @Date: 2020/11/30
 */
@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    UserDetailRepository userDetailRepo;

    @Transactional
    @Override
    public ApiResult updateDetail(String uuid, UserDetail detail) {
        UserDetail target = userDetailRepo.findByUUID(uuid);
        BeanUtils.copyProperties(detail, target, MyBeanUtils.getNullPropertyNames(detail));
        detail.setUpdateTime(new Date());
        userDetailRepo.save(detail);

        //目前问题是可以正常更新，但是会新建一列存储已更新的字段=====
        return new ApiResult(StatusEnum.SUCCESS, "个人详情信息更新成功");
    }
}
