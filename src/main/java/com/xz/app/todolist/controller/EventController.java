package com.xz.app.todolist.controller;

import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.CreateEvent;
import com.xz.app.todolist.service.impl.EventServiceImpl;
import com.xz.app.todolist.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
@RestController
@RequestMapping("/todolist")
public class EventController {

    @Autowired
    EventServiceImpl eventService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping(value = "createEvent")
    public Object createEvent(@RequestBody CreateEvent event,
                              @RequestParam String token) {

        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }

        try {
            String eventId = eventService.createEvent(event, user);
            return new ApiResult(StatusEnum.SUCCESS, eventId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(StatusEnum.FAILED_EVENT_CREATE, e.getMessage());
        }

    }

}
