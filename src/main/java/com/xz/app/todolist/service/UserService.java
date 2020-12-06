package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.ApiResult;
import org.springframework.data.domain.Page;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/11/22
 */
public interface UserService {
    ApiResult register(String name, String password, String phone);

    String login(User user, String rsaPwd,long timestamp) ;

    ApiResult logout(String userNo, String token);

    //验证密码
    boolean validatePwd(User user, String rsaPwd,long timestamp);


    User findUserNo(String userNo);

    User findUserName(String userName);

    User findUserPhone(String phone);

    User findUserToken(String token);

    User findUUID(String uuid);

    List<User> findAll();

    Page<User> getAllUserByOnlyPage(Integer page, Integer size);

    void alterUserName(String uuid, String newUserName);

    void alterUserPwd(String uuid, String newUserPwd);

}
