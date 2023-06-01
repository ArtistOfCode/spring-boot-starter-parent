package com.codeartist.component.core.api;

import java.util.List;

/**
 * 基础转换接口
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
public interface BaseConverter<DO, Param, VO> {

    DO toDo(Param param);

    VO toVo(DO role);

    List<VO> toVo(List<DO> roles);
}
