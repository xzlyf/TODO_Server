package com.xz.app.todolist.controller;

import com.xz.app.todolist.dto.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * user 控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/now")
    public ApiResult getNow(@RequestParam("user")String user) {
        return new ApiResult(0, "success", System.currentTimeMillis(),"Hello:"+user);
    }


}
