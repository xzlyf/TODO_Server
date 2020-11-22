package com.xz.app.todolist.dao;

import com.xz.app.todolist.domain.User;
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
     * 根据用户账号查询
     * <p>
     * <p>
     * 相当于    @Query(value = "select t from UserDo t where t.userNo = ?1")
     *
     * @param userNo
     * @return
     */
    User findByUserNo(String userNo);

    /**
     * 更具用户名查询
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);


    /**
     * 查询表中所有用户
     *
     * @return
     */
    List<User> findAll();


    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 更新表数据 by username
     */
    @Modifying
    @Query("update User t  set t.userName=?2,t.updateTime=?3 where t.uuid=?1")
    void updateStateByUserName(String UUID, String newUserName, Date date);


    /**
     * 更新表数据 by userPwd
     */
    @Modifying
    @Query("update User t  set t.userPwd=?2,t.updateTime=?3 where t.uuid=?1")
    void updateStateByUserPwd(String UUID, String userPwd, Date date);


}
