package com.xz.app.todolist.dao;

import com.xz.app.todolist.domain.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * user 表操作
 */
public interface UserRepository extends JpaRepository<UserDo, Long> {

    /**
     * 根据用户账号查询
     * <p>
     * <p>
     * 相当于    @Query(value = "select t from UserDo t where t.userNo = ?1")
     *
     * @param userNo
     * @return
     */
    UserDo findByUserNo(String userNo);

    /**
     * 更具用户名查询
     * @param userName
     * @return
     */
    UserDo findByUserName(String userName);


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
