package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.EventList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    @Modifying
    @Query("delete from EventList e where e.author.uuid=?1 and e.id = ?2")
    int deleteEvent(String uuid, String id);

    /**
     * 改
     */

    /**
     * 查
     */
}
