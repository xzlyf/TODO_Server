package com.xz.app.todolist.controller;

import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.PagingResult;
import com.xz.app.todolist.service.impl.UserServiceImpl;
import com.xz.app.todolist.constant.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * user 控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

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
        User user = userServiceImpl.findUserNo(userNo);
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
        User user = userServiceImpl.findUserName(userName);
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
            return new ApiResult(StatusEnum.SUCCESS, userServiceImpl.findAll());
        } else {
            return new PagingResult<>(StatusEnum.SUCCESS, userServiceImpl.getAllUserByOnlyPage(page, size));
        }
    }

    /**
     * 修改用户名
     *
     * @param UUID
     * @param newUserName
     * @return
     */
    @RequestMapping(value = "/alterUserName")
    public Object alterUserName(@RequestParam(value = "uuid") String UUID, @RequestParam(value = "name") String newUserName) {
        try {
            userServiceImpl.alterUserName(UUID, newUserName);
            return new ApiResult(StatusEnum.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("======error=======：" + e.getMessage());
            return new ApiResult(StatusEnum.FAILED_USER_UPDATE, null);
        }
    }

    /**
     * 修改用户密码
     *
     * @param UUID
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/alterUserPwd")
    public Object alterUserPwd(@RequestParam(value = "uuid") String UUID, @RequestParam(value = "pwd") String pwd) {
        try {
            userServiceImpl.alterUserPwd(UUID, pwd);
            return new ApiResult(StatusEnum.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("======error=======：" + e.getMessage());
            return new ApiResult(StatusEnum.FAILED_USER_UPDATE, null);
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
    @RequestMapping(value = "/registerUser", params = {"username", "password", "phone"})
    public Object registerUser(@RequestParam(value = "username") String name
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "phone") String phone) {
        return userServiceImpl.register(name, password, phone);
    }

    /**
     * 用户登录
     *
     * @param password
     * @param phone
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "type") String type) {

        return userServiceImpl.login(phone, password, type);
    }

    /**
     * 注销登录
     *
     * @param userNo
     * @return
     */
    @GetMapping(value = "/logout")
    public Object logout(@RequestParam(value = "userNo") String userNo,
                         @RequestParam(value = "token") String token) {
        return userServiceImpl.logout(userNo, token);
    }

    @GetMapping(value = "/update")
    public Object updateDetail(@RequestParam(value = "token") String token) {
        return userServiceImpl.updateDetail(token);
    }

}
