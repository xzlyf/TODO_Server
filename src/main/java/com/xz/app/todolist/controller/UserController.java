package com.xz.app.todolist.controller;

import com.xz.app.todolist.domain.UserDo;
import com.xz.app.todolist.dto.ApiResult;
import com.xz.app.todolist.service.UserService;
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
            return new ApiResult(0, "success", System.currentTimeMillis());
        } else {
            return new ApiResult(0, "success", System.currentTimeMillis(), "Hello:" + user);
        }
    }

    @RequestMapping("")
    public Object getUser(@RequestParam("userno") long userno) {
        UserDo userDo = userService.findByUserNo(userno);
        if (userDo == null) {
            return new ApiResult(1,"fail","用户不存在");
        } else {
            return new ApiResult(0,"success",userDo);
        }
    }


}
