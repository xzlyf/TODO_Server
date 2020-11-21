package com.xz.app.todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
@Data
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

}