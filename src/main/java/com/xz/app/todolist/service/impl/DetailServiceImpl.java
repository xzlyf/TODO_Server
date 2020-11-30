package com.xz.app.todolist.service.impl;

import com.alibaba.fastjson.JSON;
import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.User;
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

    /**
     * 更新用户信息
     *
     * @param uuid
     * @param detail
     * @return
     */
    @Transactional
    @Override
    public ApiResult updateDetail(String uuid, UserDetail detail) {

        //防止被改id
        detail.setId(null);
        UserDetail target = userDetailRepo.findByUUID(uuid);
        if (target == null) {
            //没有找到对应的用户数据，创建新的用户信息
            target = saveDetail(uuid, detail);
        }
        BeanUtils.copyProperties(detail, target, MyBeanUtils.getNullPropertyNames(detail));
        detail.setUpdateTime(new Date());
        userDetailRepo.updateDetail(uuid, target);

        //目前问题是可以正常更新，但是会新建一列存储已更新的字段=====
        return new ApiResult(StatusEnum.SUCCESS, "个人详情信息更新成功");
    }

    /**
     * 创建用户信息
     *
     * @param uuid
     * @param detail
     * @return
     */
    @Override
    public UserDetail saveDetail(String uuid, UserDetail detail) {
        User user = new User();
        user.setUuid(uuid);
        detail.setUser(user);
        return userDetailRepo.save(detail);
    }
}
