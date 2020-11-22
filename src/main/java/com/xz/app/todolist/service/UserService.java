package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/11/22
 */
public interface UserService {

    User findUserNo(String userNo);

    User finUserName(String userName);

    List<User> findAll();

    Page<User> getAllUserByOnlyPage(Integer page, Integer size);

    User addUser(String name, String password, String phone);

    void alterUserName(String uuid, String newUserName);

    void alterUserPwd(String uuid, String newUserPwd);

}
