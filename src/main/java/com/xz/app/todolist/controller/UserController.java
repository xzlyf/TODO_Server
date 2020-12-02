package com.xz.app.todolist.controller;

import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.UserDetail;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.PagingResult;
import com.xz.app.todolist.service.impl.DetailServiceImpl;
import com.xz.app.todolist.service.impl.UserServiceImpl;
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
    @Autowired
    private DetailServiceImpl detailServiceImpl;

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
     * 注册用户
     *
     * @param name
     * @param password
     * @param phone
     * @return
     */
    @RequestMapping(value = "/registerUser", params = {"username", "password", "phone"})
    public Object registerUser(@RequestParam(value = "username") String name,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "phone") String phone) {
        return userServiceImpl.register(name, password, phone);
    }

    /**
     * 用户登录
     *
     * @param type 1-手机登录  2-账号登录 3-token登录
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(
            @RequestParam(value = "account") String account,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "type") Integer type) {

        User user = null;
        switch (type) {
            case 1:
                //手机号登录
                user = userServiceImpl.findUserPhone(account);
                if (user == null) {
                    return new ApiResult(StatusEnum.FAILED_USER_LOGIN_NO_USER_PHONE, null);
                }
                break;
            case 2:
                //账号登录
                user = userServiceImpl.findUserNo(account);
                if (user == null) {
                    return new ApiResult(StatusEnum.FAILED_USER_LOGIN_NO_USER_NO, null);
                }
                break;
            case 3:
                //token登录 登录账号默认使用的是手机号
                user = userServiceImpl.findUserToken(account);
                if (user == null) {
                    return new ApiResult(StatusEnum.FAILED_USER_LOGIN_NO_USER_NO, null);
                }
                if (!password.equalsIgnoreCase(user.getToken())) {
                    //token过期
                    return new ApiResult(StatusEnum.ERROR_TOKEN, null);
                } else {
                    //未过期
                    return new ApiResult(StatusEnum.SUCCESS, password);
                }
            default:
                //参数错误
                return new ApiResult(StatusEnum.ERROR_PARAMS, null);
        }

        try {
            String newToken = userServiceImpl.login(user, password);
            if (newToken == null) {
                //密码不正确
                return new ApiResult(StatusEnum.FAILED_USER_LOGIN, null);
            } else {
                //返回新的token
                return new ApiResult(StatusEnum.SUCCESS, newToken);
            }
        } catch (Exception e) {
            return new ApiResult(StatusEnum.ERROR, e.getMessage());
        }
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

    /**
     * 更新用户信息
     *
     * @param detail 接收user detail 实体json数据
     * @return
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public Object updateDetail(@RequestBody UserDetail detail,
                               @RequestParam String token) {

        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        try {
            detailServiceImpl.updateDetail(user.getUuid(), detail);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(StatusEnum.FAILED_USER_DETAIL_UPDATE, null);
        }
        return new ApiResult(StatusEnum.SUCCESS, null);
    }

    /**
     * 修改用户密码
     *
     * @param token
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/alterPwd")
    public Object alterUserPwd(@RequestParam(value = "token") String token, @RequestParam(value = "pwd") String pwd) {
        try {
            userServiceImpl.alterUserPwd(token, pwd);
            return new ApiResult(StatusEnum.SUCCESS, null);
        } catch (Exception e) {
            System.out.println("======error=======：" + e.getMessage());
            return new ApiResult(StatusEnum.FAILED_USER_UPDATE, null);
        }
    }


}
