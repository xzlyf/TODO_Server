package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.pojo.EventList;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.CreateEvent;
import com.xz.app.todolist.repository.EventRepository;
import com.xz.app.todolist.service.EventService;
import com.xz.app.todolist.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;


    /**
     * 创建事件
     * @return 返回事件id
     */
    @Override
    public String createEvent(CreateEvent event, User user) {
        EventList newEvent = new EventList();
        newEvent.setAuthor(user);
        //把已修改数据保存到目标对象
        BeanUtils.copyProperties(event, newEvent, MyBeanUtils.getNullPropertyNames(event));
        EventList targetEvent = eventRepository.save(newEvent);
        return targetEvent.getId();
    }

    @Override
    public void deleteEvent(String id) {

    }

    @Override
    public void updateEvent(CreateEvent event, String id) {

    }

    @Override
    public void setDone(boolean isDone, String id) {

    }

    @Override
    public Page<EventList> getUserAllEvent(Integer page, Integer size) {
        return null;
    }
}
