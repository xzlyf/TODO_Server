package com.xz.app.todolist.repository;

import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;
import com.xz.app.todolist.pojo.AppInfo;
import com.xz.app.todolist.pojo.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

}
