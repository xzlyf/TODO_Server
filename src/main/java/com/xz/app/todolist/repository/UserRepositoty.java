package com.xz.app.todolist.repository;

import com.xz.app.todolist.controller.UserController;
import com.xz.app.todolist.domain.UserDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * user 表操作
 */
public interface UserRepositoty extends JpaRepository<UserController, Long> {

    /*
     * 根据用户名查询
     * */
    @Query("select t from User t where t.name = :name")
    UserDo findByUserName(@Param("name") String name);

}
