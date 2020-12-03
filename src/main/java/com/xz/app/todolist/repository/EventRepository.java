package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    /**
     * 增
     */

    /**
     * 删
     */


    @Modifying
    @Query("delete from Event e where e.author.uuid=?1 and e.id = ?2")
    int deleteEvent(String uuid, String id);

    /**
     * 改
     */

    @Modifying
    @Query("update Event e set e=?1 where e.author.uuid=?2 and e.id=?3")
    int updateEvent(Event event, String uuid, String id);

    @Modifying
    @Query("update Event e set e.done=?1 where e.author.uuid=?2 and e.id=?3")
    int updateDone(Boolean isDone, String uuid, String id);


    /**
     * 查
     */

    Event findById(String id);

    @Query("select e from Event e where e.author.uuid=?1")
    Page<Event> findByUuid(String uuid, Pageable pageable);

}
