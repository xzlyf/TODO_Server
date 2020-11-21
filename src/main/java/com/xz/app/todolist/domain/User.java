package com.xz.app.todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户实体类
 */

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键自动生成uuid
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "system-uuid")
    @JsonIgnore//作用：在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口。
    @Column(name = "uuid", length = 32)
    private String uuid;

    @Column(name = "user_no", length = 16)
    private String userNo;

    @Column(name = "user_name", length = 32)
    private String userName;

    @JsonIgnore
    @Column(name = "user_pwd", length = 32)
    private String userPwd;

    @Column(name = "user_phone", length = 16)
    private String userPhone;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}