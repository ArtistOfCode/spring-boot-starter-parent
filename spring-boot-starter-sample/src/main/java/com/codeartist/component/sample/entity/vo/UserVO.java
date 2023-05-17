package com.codeartist.component.sample.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 用户基本信息
 *
 * @author CodeGenerator
 * @since 2023-02-20
 */
@Getter
@Setter
@ApiModel(description = "用户基本信息")
public class UserVO {

    private Long id;

    @ApiModelProperty("真实姓名")
    private String name;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("状态：1：删除，0：有效")
    private Integer deleted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("测试日期")
    private LocalDate testDate;

    @ApiModelProperty("测试时间")
    private LocalTime testTime;
}
