package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.UserDetail;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.UserPublicDataVO;
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

    String login(User user, String rsaPwd) throws InvalidKeySpecException, NoSuchAlgorithmException;

    ApiResult logout(String userNo, String token);


    User findUserNo(String userNo);

    User findUserName(String userName);

    User findUserPhone(String phone);

    User findUserToken(String token);

    List<User> findAll();

    Page<User> getAllUserByOnlyPage(Integer page, Integer size);

    void alterUserName(String uuid, String newUserName);

    void alterUserPwd(String uuid, String newUserPwd);

}
