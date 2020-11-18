package com.xz.app.todolist.domain;

import org.hibernate.annotations.Synchronize;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

/**
 * 用户实体类
 */

@Entity
@Table(name = "user")
public class UserDo {
    @Id
    @GeneratedValue
    @Column(name = "uuid", length = 32)
    private String uuid;

    @Column(name = "user_no",length = 16)
    private long userNo;

    @Column(name = "user_name",length = 32)
    private String userName;

    @Column(name = "user_pwd",length = 32)
    private String userPwd;

    @Column(name = "user_phone",length = 16)
    private String userPhone;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getUserNo() {
        return userNo;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}