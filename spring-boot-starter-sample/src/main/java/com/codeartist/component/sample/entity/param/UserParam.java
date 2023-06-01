package com.codeartist.component.sample.entity.param;

import com.codeartist.component.core.entity.PageParam;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户基本信息
 *
 * @author CodeGenerator
 * @since 2023-02-20
 */
@Getter
@Setter
public class UserParam extends PageParam {

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
