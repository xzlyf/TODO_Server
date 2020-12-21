package com.xz.app.todolist.controller;

import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.vo.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xz
 * @Date: 2020/12/19
 * 主页跳转
 */
@RestController
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/")
    public Object index() {
        //待完成 - 开启spring boot 页面跳转 关键字spring mvc
        return new ApiResult(StatusEnum.SUCCESS, "主页测试");
    }
}
