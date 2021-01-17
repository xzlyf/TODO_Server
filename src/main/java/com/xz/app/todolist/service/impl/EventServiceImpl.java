package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.pojo.Event;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.CreateEvent;
import com.xz.app.todolist.repository.EventRepository;
import com.xz.app.todolist.service.EventService;
import com.xz.app.todolist.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     *
     * @return 返回事件id
     */
    @Override
    public String createEvent(CreateEvent event, User user) {
        Event newEvent = new Event();
        newEvent.setAuthor(user);
        //把已修改数据保存到目标对象
        BeanUtils.copyProperties(event, newEvent, MyBeanUtils.getNullPropertyNames(event));
        Event targetEvent = eventRepository.save(newEvent);
        return targetEvent.getId();
    }

    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public boolean deleteEvent(String uuid, String id) {
        int i = eventRepository.deleteEvent(uuid, id);
        //等于0是没有删除成功或者找不到对应id的事件
        return !(i == 0);
    }

    @Transactional
    @Override
    public boolean updateEvent(CreateEvent event, String uuid, String id) {
        Event oldEvent = eventRepository.findById(id);
        if (oldEvent == null) {
            return false;
        }
        //把已修改数据保存到目标对象
        BeanUtils.copyProperties(event, oldEvent, MyBeanUtils.getNullPropertyNames(event));
        int i = eventRepository.updateEvent(oldEvent, uuid, id);
        //等于0更新失败
        return !(i == 0);
    }

    @Transactional
    @Override
    public boolean updateStatus(boolean isDone, String uuid, String id) {
        Event event = eventRepository.findById(id);
        if (event == null) {
            return false;
        }
        int i = eventRepository.updateDone(isDone, uuid, id);
        //等于0更新失败
        return !(i == 0);
    }

    @Override
    public Page<Event> getUserAllEvent(Integer page, Integer size, String uuid) {
        Sort sort = Sort.by(Sort.Order.desc("createTime"));//根据createTime字段降序排列
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        //方法一：无条件分页查询
        //return eventRepository.findAll(pageable);
        //方法二：specification复杂条件分页查询
        //Specification<Event> specification = new Specification<Event>() {
        //    @Override
        //    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        //        List<Predicate> list = new ArrayList<Predicate>();
        //        //条件1
        //        //list.add(cb.like(root.get("content").as(String.class), "%" + uuid + "%"));
        //        //条件2
        //        //list.add(cb.like(root.get("uuid").as(String.class), "%" + uuid + "%"));
        //        return cb.and(list.toArray(new Predicate[list.size()]));
        //    }
        //};
        //return eventRepository.findAll(specification, pageable);

        //方法三：自定义repo分页查询
        return eventRepository.findByUuid(uuid, pageable);
    }

    @Override
    public Page<Event> getDoneEvent(Integer page, Integer size, String uuid, Boolean isDone) {
        Sort sort = Sort.by(Sort.Order.desc("createTime"));//根据createTime字段降序排列
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return eventRepository.findDoneEvent(uuid, isDone, pageable);
    }
}
