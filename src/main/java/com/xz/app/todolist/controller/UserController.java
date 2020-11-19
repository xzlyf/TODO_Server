package com.xz.app.todolist.controller;

import com.xz.app.todolist.domain.UserDo;
import com.xz.app.todolist.dto.ApiResult;
import com.xz.app.todolist.service.UserService;
import com.xz.app.todolist.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * user 控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/now")
    public Object getNow(String user) {
        if (user == null) {
            return new ApiResult(StatusEnum.SUCCESS, System.currentTimeMillis());
        } else {
            return new ApiResult(StatusEnum.SUCCESS, System.currentTimeMillis(), "Hello:" + user);
        }
    }

    @RequestMapping("")
    public Object getUser(@RequestParam("userno") long userno) {
        UserDo userDo = userService.findByUserNo(userno);
        if (userDo == null) {
            return new ApiResult(StatusEnum.NULL_USER, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, userDo);
        }
    }


}
