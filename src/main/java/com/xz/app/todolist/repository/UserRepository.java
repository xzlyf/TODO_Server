package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * user 表操作
 */
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * ========增=========
     */
    //插入用户
    User save(User user);


    /**
     * ========删=========
     */


    /**
     * ========改=========
     */
    //更新表数据 by username
    @Modifying
    @Query("update User t  set t.userName=?2,t.updateTime=?3 where t.uuid=?1")
    void updateStateByUserName(String UUID, String newUserName, Date date);


    //更新表数据 by userPwd
    @Modifying
    @Query("update User t  set t.userPwd=?2,t.updateTime=?3 where t.uuid=?1")
    void updateStateByUserPwd(String UUID, String userPwd, Date date);


    /**
     * ========查=========
     */
    //用户账号查询
    //相当于    @Query(value = "select t from UserDo t where t.userNo = ?1")
    User findByUserNo(String userNo);

    //用户名查询
    User findByUserName(String userName);

    //手机号查询
    User findByUserPhone(String userPhone);


    //查询表中所有用户
    List<User> findAll();


}
