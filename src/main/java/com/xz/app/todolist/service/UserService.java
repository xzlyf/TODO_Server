package com.xz.app.todolist.service;

import com.xz.app.todolist.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * user业务层
 */
public class UserService {
    @Autowired
    UserRepositoty userRepositoty;
}
