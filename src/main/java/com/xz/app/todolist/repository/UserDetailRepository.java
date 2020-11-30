package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/11/27
 * user_detail表  dao
 */
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    /**
     * 增
     */

    /**
     * 删
     */

    /**
     * 改
     */

    /**
     * 查
     */

    @Query("select d from UserDetail d join d.user u  where  u.uuid=?1 ")
    UserDetail findByUUID(@Param("uuid") String uuid);
}
