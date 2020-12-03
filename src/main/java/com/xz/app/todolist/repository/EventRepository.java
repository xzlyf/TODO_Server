package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.EventList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
public interface EventRepository extends JpaRepository<EventList, Long> {

    /**
     * 增
     */

    /**
     * 删
     */

    int deleteById(String id);

    /**
     * 改
     */

    /**
     * 查
     */
}
