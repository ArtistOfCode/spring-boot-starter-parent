package com.codeartist.component.sample.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 用户基本信息
 *
 * @author CodeGenerator
 * @since 2023-02-27
 */
@Getter
@Setter
@TableName("t_user")
public class User {

    private Long id;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态：1：删除，0：有效
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 测试日期
     */
    private LocalDate testDate;

    /**
     * 测试时间
     */
    private LocalTime testTime;
}
