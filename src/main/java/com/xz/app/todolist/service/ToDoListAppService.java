package com.xz.app.todolist.service;

import com.xz.app.todolist.domain.UserDo;
import com.xz.app.todolist.dao.UserRepository;
import com.xz.app.todolist.utils.AccountGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user业务层
 */
@Service
public class ToDoListAppService {
    @Autowired
    UserRepository userRepository;

    /**
     * 根据账号查询用户信息
     */
    public UserDo findUserNo(String userNo) {
        UserDo user = null;
        try {
            user = userRepository.findByUserNo(userNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    public UserDo finUserName(String userName) {
        UserDo user = null;
        try {
            user = userRepository.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 查询表里所有用户
     */
    public List<UserDo> findUserNo() {
        List<UserDo> allList = null;
        try {
            allList = userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allList;
    }

    /**
     * 保存为新的用户
     */
    public UserDo addUser(String name, String password, String phone) {
        UserDo userDo = new UserDo();
        userDo.setUserName(name);
        userDo.setUserPwd(password);
        userDo.setUserPhone(phone);
        //生成账号，并查询数据库是否存在此账号，如果存在则重新生成账号
        String tempAccount;
        UserDo checkAgain;
        do {
            tempAccount = AccountGenerate.makeAccount(8);
            checkAgain = findUserNo(tempAccount);
        } while (checkAgain != null);
        userDo.setUserNo(tempAccount);
        try {
            return userRepository.save(userDo);
        } catch (Exception e) {
            //数据插入失败，可能存在相同项
            System.out.println("=========error==========:" + e.getMessage());
        }
        return null;
    }
}
