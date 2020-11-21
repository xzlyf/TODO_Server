package com.xz.app.todolist.repository;

import com.xz.app.todolist.domain.UserDo;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * user 表操作
 */
public interface UserRepository extends JpaRepository<UserDo, Long> {

    /**
     * 根据用户账号或者用户名查询
     *
     * @param userNoOrName
     * @return
     */
    UserDo findByUserNo(String userNoOrName);


    /**
     * 查询表中所有用户
     *
     * @return
     */
    List<UserDo> findAll();


    /**
     * 插入用户
     *
     * @param userDo
     * @return
     */
    UserDo save(UserDo userDo);
}
