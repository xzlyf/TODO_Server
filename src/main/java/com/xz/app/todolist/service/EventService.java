package com.xz.app.todolist.service;

import com.xz.app.todolist.pojo.Event;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.CreateEvent;
import org.springframework.data.domain.Page;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
public interface EventService {

    //创建事件
    String createEvent(CreateEvent event, User user);

    //删除事件
    boolean deleteEvent(String uuid, String id);

    //修改事件
    boolean updateEvent(CreateEvent event, String uuid, String id);

    //修改事件状态 完成/未完成
    boolean updateStatus(boolean isDone, String uuid, String id);

    //获取一个用户所有的事件
    Page<Event> getUserAllEvent(Integer page, Integer size);


}
