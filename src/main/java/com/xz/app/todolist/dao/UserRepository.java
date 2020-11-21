package com.xz.app.todolist.dao;

import com.xz.app.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
