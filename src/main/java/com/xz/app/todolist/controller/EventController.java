package com.xz.app.todolist.controller;

import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.CreateEvent;
import com.xz.app.todolist.pojo.vo.PagingResult;
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
    @PostMapping(value = "/createEvent", produces = "application/json;charset=UTF-8")
    public Object createEvent(@RequestBody CreateEvent event,
                              @RequestParam String token) {

        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        if (event == null) {
            return new ApiResult(StatusEnum.FAILED_NULL_PARAMS, null);
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
    @GetMapping(value = "/deleteEvent")
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
    @PostMapping(value = "/updateEvent", produces = "application/json;charset=UTF-8")
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

    /**
     * 更新事件状态
     */
    @GetMapping(value = "/setStatus")
    public Object setStatus(@RequestParam Boolean status,
                            @RequestParam String token,
                            @RequestParam String id) {
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

    /**
     * 指定一个用户查询所有事件
     * 分页查询
     */
    @GetMapping(value = "/getAllEvent")
    public Object getAllEvent(@RequestParam Integer page,
                              @RequestParam Integer size,
                              @RequestParam String token) {

        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        try {
            return new PagingResult<>(StatusEnum.SUCCESS, eventService.getUserAllEvent(page, size, user.getUuid()));
        } catch (Exception e) {
            return new ApiResult(StatusEnum.ERROR, e.getMessage());
        }
    }

    /**
     * 获取 完成事件或未完成事件
     * 支持分页查询
     *
     * @param token 用户token
     * @param done  true 完成 未完成
     */
    @GetMapping(value = "/getDoneEvent")
    public Object getDoneEvent(@RequestParam Integer page,
                               @RequestParam Integer size,
                               @RequestParam String token,
                               @RequestParam Boolean done) {
        User user = userServiceImpl.findUserToken(token);
        if (user == null) {
            return new ApiResult(StatusEnum.ERROR_TOKEN, null);
        }
        try {
            return new PagingResult<>(StatusEnum.SUCCESS, eventService.getDoneEvent(page, size, user.getUuid(), done));
        } catch (Exception e) {
            return new ApiResult(StatusEnum.ERROR, e.getMessage());
        }
    }

}
