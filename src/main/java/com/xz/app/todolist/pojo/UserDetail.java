package com.xz.app.todolist.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: xz
 * @Date: 2020/11/26
 * <p>
 * 用户详细信息表
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "user_detail")
@EntityListeners(AuditingEntityListener.class)
public class UserDetail {
    /**
     * 主键自动生成id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore//作用：在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口。
    @Column(name = "id", length = 32)
    private Long id;

    @Column(name = "nick_name", length = 32)
    private String nickName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "sex", length = 8)
    private String sex;

    @Column(name = "site")
    private String site;

    @Column(name = "profession", length = 64)
    private String profession;

    @Column(name = "company", length = 64)
    private String company;

    @Column(name = "description")
    private String description;

    //如果不需要根据Address级联查询People，可以注释掉
    //@OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    //private User user;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
