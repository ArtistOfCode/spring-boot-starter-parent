package com.codeartist.component.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 角色关联信息
 *
 * @author 艾江南
 * @since 2023-03-01
 */
@Getter
@Setter
public class RelationVO {

    private Long id;

    private Set<Long> ids;
}
