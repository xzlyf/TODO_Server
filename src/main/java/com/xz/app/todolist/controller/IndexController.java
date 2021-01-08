package com.xz.app.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xz
 * @Date: 2020/12/19
 * 主页跳转
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "maintain";
    }

    /**
     * app使用条例
     */
    @GetMapping("/privacy")
    public String privacy() {
        return "privacy";
    }
}
