package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xz
 * @Date: 2020/11/27
 * user_detail表  dao
 */
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
