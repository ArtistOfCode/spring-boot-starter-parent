package com.codeartist.component.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 角色关联参数
 *
 * @author 艾江南
 * @since 2023-03-01
 */
@Getter
@Setter
public class RelationParam {

    @NotNull
    private Long id;

    @NotNull
    private Set<Long> ids;

    @NotNull
    private Integer type;
}
