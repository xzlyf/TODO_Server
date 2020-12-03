package com.xz.app.todolist.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @Date: 2020/12/3
 * 待办事件表
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "event_list")
@EntityListeners(AuditingEntityListener.class)
public class EventList {
    /**
     * 事件唯一id
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 短标题
     * 限制64字节
     */
    @Column(name = "short_title", length = 64)
    private String shortTitle;

    /**
     * 事件类容
     * 限制512字节
     */
    @Column(name = "content", length = 512)
    private String content;

    /**
     * 是否已完成
     */
    @Column(name = "done",columnDefinition = "boolean default false")
    private Boolean done;

    /**
     * 提醒时间
     */
    @Column(name = "ramind_time")
    private Date remindTime;

    /**
     * 外键 对应uuid
     */
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示author不能为空。删除事件，不影响用户
    @JoinColumn(name="uuid")//设置在article表中的关联字段(外键)
    private User author;//所属作者


    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
