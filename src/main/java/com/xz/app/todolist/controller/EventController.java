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

    /**
     * 创建事件
     */
    @PostMapping(value = "createEvent", produces = "application/json;charset=UTF-8")
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

    /**
     * 删除事件
     *
     * @param id 事件id
     */
    @GetMapping(value = "deleteEvent")
    public Object deleteEvent(@RequestParam String id,
                              @RequestParam String token) {
        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        try {
            if (eventService.deleteEvent(user.getUuid(), id)) {
                return new ApiResult(StatusEnum.SUCCESS, null);
            } else {
                return new ApiResult(StatusEnum.FAILED_EVENT_NULL, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResult(StatusEnum.FAILED_EVENT_DELETE, e.getMessage());
        }
    }


    /**
     * 更新事件
     */
    @PostMapping(value = "updateEvent", produces = "application/json;charset=UTF-8")
    public Object updateEvent(@RequestBody CreateEvent event,
                              @RequestParam String token,
                              @RequestParam String id) {
        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        try {
            if (eventService.updateEvent(event, user.getUuid(), id)) {
                return new ApiResult(StatusEnum.SUCCESS, null);
            }
            return new ApiResult(StatusEnum.FAILED_EVENT_NULL, null);
        } catch (Exception e) {
            return new ApiResult(StatusEnum.FAILED_EVENT_UPDATE, e.getMessage());
        }

    }

    @GetMapping(value = "setStatus")
    public Object setStatus(@RequestParam Boolean status,
                            @RequestParam String token,
                            @RequestParam String id){
        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        try {
            if (eventService.updateStatus(status, user.getUuid(), id)) {
                return new ApiResult(StatusEnum.SUCCESS, null);
            }
            return new ApiResult(StatusEnum.FAILED_EVENT_NULL, null);
        } catch (Exception e) {
            return new ApiResult(StatusEnum.FAILED_EVENT_UPDATE, e.getMessage());
        }
    }

}
