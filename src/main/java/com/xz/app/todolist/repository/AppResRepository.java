package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.AppRes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xz
 * @Date: 2020/12/8
 */
public interface AppResRepository extends JpaRepository<AppRes, Long> {
    AppRes findByAppId(String appId);
}
