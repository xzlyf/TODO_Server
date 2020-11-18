package com.xz.app.todolist.service;

import com.xz.app.todolist.domain.UserDo;
import com.xz.app.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * user业务层
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * 根据账号查询用户信息
     * @param userNo
     * @return
     */
    public UserDo findByUserNo(long userNo){
        UserDo user = null;

        try{
            user = userRepository.findByUserNo(userNo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
