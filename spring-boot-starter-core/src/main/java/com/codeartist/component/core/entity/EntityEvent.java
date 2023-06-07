package com.codeartist.component.core.entity;

import com.codeartist.component.core.entity.enums.GlobalConstants.EntityEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 实体修改事件
 *
 * @author J.N.AI
 * @date 2023/6/7
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityEvent<DO> {

    private EntityEventType type;
    private DO entity;
}
