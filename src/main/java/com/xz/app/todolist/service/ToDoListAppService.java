package com.xz.app.todolist.service;

import com.xz.app.todolist.dao.UserRepository;
import com.xz.app.todolist.domain.User;
import com.xz.app.todolist.utils.AccountGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user业务层
 */
@Service
public class ToDoListAppService {
    @Autowired
    UserRepository UserDao;

    /**
     * 根据账号查询用户信息
     */
    public User findUserNo(String userNo) {
        User user = null;
        try {
            user = UserDao.findByUserNo(userNo);
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
    public User finUserName(String userName) {
        User user = null;
        try {
            user = UserDao.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 查询表里所有用户
     */
    public List<User> findAll() {
        List<User> allList = null;
        try {
            allList = UserDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allList;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public Page<User> getAllUserByOnlyPage(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.desc("createTime"));//根据createTime字段降序排列
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return UserDao.findAll(pageable);
    }

    /**
     * 保存为新的用户
     */
    public User addUser(String name, String password, String phone) {
        User user = new User();
        user.setUserName(name);
        user.setUserPwd(password);
        user.setUserPhone(phone);
        //生成账号，并查询数据库是否存在此账号，如果存在则重新生成账号
        String tempAccount;
        User checkAgain;
        do {
            tempAccount = AccountGenerate.makeAccount(8);
            checkAgain = findUserNo(tempAccount);
        } while (checkAgain != null);
        user.setUserNo(tempAccount);
        try {
            return UserDao.save(user);
        } catch (Exception e) {
            //数据插入失败，可能存在相同项
            System.out.println("=========error==========:" + e.getMessage());
        }
        return null;
    }
}
