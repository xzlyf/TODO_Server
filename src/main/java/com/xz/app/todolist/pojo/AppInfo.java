package com.xz.app.todolist.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: xz
 * @Date: 2020/12/4
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "app_info")
@EntityListeners(AuditingEntityListener.class)
public class AppInfo {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "app_id", length = 64, unique = true, nullable = true)
    private String appid;

    @Column(name = "app_name", length = 64)
    private String appName;

    @Column(name = "version_name", length = 32)
    private String versionName;

    @Column(name = "version_code")
    private int versionCode;

    @Column(name = "update_msg", length = 1024)
    private String updateMsg;

    @Column(name = "close", columnDefinition = "boolean default false")
    private Boolean isClose;

    @Column(name = "close_msg", length = 1024)
    private String closeMsg;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
