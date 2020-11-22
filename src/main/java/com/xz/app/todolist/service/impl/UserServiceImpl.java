package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.repository.UserRepository;
import com.xz.app.todolist.service.UserService;
import com.xz.app.todolist.utils.AccountGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * user业务层
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;

    /**
     * 根据账号查询用户信息
     */
    @Override
    public User findUserNo(String userNo) {
        User user = null;
        try {
            user = userRepo.findByUserNo(userNo);
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
    @Override
    public User finUserName(String userName) {
        User user = null;
        try {
            user = userRepo.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 查询表里所有用户
     */
    @Override
    public List<User> findAll() {
        List<User> allList = null;
        try {
            allList = userRepo.findAll();
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
    @Override
    public Page<User> getAllUserByOnlyPage(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.desc("createTime"));//根据createTime字段降序排列
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return userRepo.findAll(pageable);
    }

    /**
     * 保存为新的用户
     */
    @Override
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
            return userRepo.save(user);
        } catch (Exception e) {
            //数据插入失败，可能存在相同项
            System.out.println("=========error==========:" + e.getMessage());
        }
        return null;
    }


    /**
     * 更新用户名
     * @param uuid
     * @param newUserName
     */
    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public void alterUserName(String uuid,String newUserName){
        userRepo.updateStateByUserName(uuid,newUserName,new Date(System.currentTimeMillis()));
    }

    /**
     * 更新密码
     * @param uuid
     * @param newUserPwd
     */
    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public void alterUserPwd(String uuid,String newUserPwd){
        userRepo.updateStateByUserPwd(uuid,newUserPwd,new Date(System.currentTimeMillis()));
    }
}
