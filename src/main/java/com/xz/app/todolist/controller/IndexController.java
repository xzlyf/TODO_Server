package com.xz.app.todolist.controller;

import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.vo.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xz
 * @Date: 2020/12/19
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public Object index() {
        return new ApiResult(StatusEnum.SUCCESS, "主页测试");
    }
}
