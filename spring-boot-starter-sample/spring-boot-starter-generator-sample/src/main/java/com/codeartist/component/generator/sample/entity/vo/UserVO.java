package com.codeartist.component.generator.sample.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户基本信息
 *
 * @author CodeGenerator
 * @since 2023-06-12
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
}
