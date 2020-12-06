package com.xz.app.todolist.repository;

import com.xz.app.todolist.pojo.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xz
 * @Date: 2020/11/27
 */
public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {
    /**
     * 增
     */
    /**
     * 删
     */

    /**
     * 改
     */

    /**
     * 查
     */

    AppInfo findByAppid(String uuid);



}
