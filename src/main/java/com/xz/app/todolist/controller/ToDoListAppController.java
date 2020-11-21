package com.xz.app.todolist.controller;

import com.xz.app.todolist.domain.UserDo;
import com.xz.app.todolist.dto.ApiResult;
import com.xz.app.todolist.service.ToDoListAppService;
import com.xz.app.todolist.utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * user 控制层
 */
@RestController
@RequestMapping("/todolist")
public class ToDoListAppController {

    @Autowired
    private ToDoListAppService toDoListAppService;

    /**
     * 获取当前服务器时间
     *
     * @param user
     * @return
     */
    @RequestMapping("/now")
    public Object getNow(String user) {
        if (user == null) {
            return new ApiResult(StatusEnum.SUCCESS, System.currentTimeMillis());
        } else {
            return new ApiResult(StatusEnum.SUCCESS, System.currentTimeMillis(), "Hello:" + user);
        }
    }

    /**
     * 根据账号查询用户信息
     *
     * @param userNo
     * @return
     */
    @RequestMapping("")
    public Object getUser(@RequestParam("userno") String userNo) {
        UserDo userDo = toDoListAppService.findUserNo(userNo);
        if (userDo == null) {
            return new ApiResult(StatusEnum.NULL_USER, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, userDo);
        }
    }
    /**
     * 以用户名查询是否存在用户
     * @param userName
     * @return
     */
    @RequestMapping(value = "", params = {"username"})
    public Object checkUserName(@RequestParam(value = "username") String userName) {
        UserDo userDo = toDoListAppService.finUserName(userName);
        if (userDo == null) {
            return new ApiResult(StatusEnum.NULL_USER, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, userDo);
        }
    }

    /**
     * 获取表所有用户数据
     *
     * @return
     */
    @RequestMapping("/getAllUser")
    public Object getAllUser() {
        return new ApiResult(StatusEnum.SUCCESS, toDoListAppService.findUserNo());
    }

    /**
     * 新增用户
     *
     * @param name
     * @param password
     * @param phone
     * @return
     */
    @RequestMapping(value = "/addUser",params = {"username","password","phone"})
    public Object addUser(@RequestParam(value = "username") String name
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "phone") String phone) {
        UserDo userDo = toDoListAppService.addUser(name, password, phone);
        if (userDo == null) {
            return new ApiResult(StatusEnum.FAILED_USER_ADD, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, userDo);
        }
    }




}
