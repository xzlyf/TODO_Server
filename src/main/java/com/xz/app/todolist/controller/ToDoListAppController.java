package com.xz.app.todolist.controller;

import com.xz.app.todolist.domain.User;
import com.xz.app.todolist.dto.ApiResult;
import com.xz.app.todolist.dto.PagingResult;
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
        User user = toDoListAppService.findUserNo(userNo);
        if (user == null) {
            return new ApiResult(StatusEnum.NULL_USER, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, user);
        }
    }

    /**
     * 以用户名查询是否存在用户
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "", params = {"username"})
    public Object checkUserName(@RequestParam(value = "username") String userName) {
        User user = toDoListAppService.finUserName(userName);
        if (user == null) {
            return new ApiResult(StatusEnum.NULL_USER, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, user);
        }
    }

    /**
     * 获取表所有用户数据
     *
     * @return
     */
    @RequestMapping("/getAllUser")
    public Object getAllUser(Integer page, Integer size) {
        if (page == null || size == null) {
            return new ApiResult(StatusEnum.SUCCESS, toDoListAppService.findAll());
        } else {
            return new PagingResult<>(StatusEnum.SUCCESS, toDoListAppService.getAllUserByOnlyPage(page, size));
        }
    }


    /**
     * 新增用户
     *
     * @param name
     * @param password
     * @param phone
     * @return
     */
    @RequestMapping(value = "/addUser", params = {"username", "password", "phone"})
    public Object createUser(@RequestParam(value = "username") String name
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "phone") String phone) {
        User user = toDoListAppService.addUser(name, password, phone);
        if (user == null) {
            return new ApiResult(StatusEnum.FAILED_USER_ADD, null);
        } else {
            return new ApiResult(StatusEnum.SUCCESS, user);
        }
    }

    /**
     * 修改用户名
     * @param UUID
     * @param newUserName
     * @return
     */
    @RequestMapping(value = "/alterUserName")
    public Object alterUserName(@RequestParam(value = "uuid") String UUID, @RequestParam(value = "name") String newUserName) {
        try {
            toDoListAppService.alterUserName(UUID, newUserName);
            return new ApiResult(StatusEnum.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("======error=======：" + e.getMessage());
            return new ApiResult(StatusEnum.FAILED_USER_UPDATE, null);
        }
    }

    /**
     * 修改用户密码
     * @param UUID
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/alterUserPwd")
    public Object alterUserPwd(@RequestParam(value = "uuid") String UUID, @RequestParam(value = "pwd") String pwd) {
        try {
            toDoListAppService.alterUserPwd(UUID, pwd);
            return new ApiResult(StatusEnum.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("======error=======：" + e.getMessage());
            return new ApiResult(StatusEnum.FAILED_USER_UPDATE, null);
        }
    }


}
