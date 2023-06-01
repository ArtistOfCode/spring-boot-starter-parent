package com.codeartist.component.core.api;

import com.codeartist.component.core.entity.PageInfo;

/**
 * 基础服务类
 *
 * @author 艾江南
 * @since 2022-08-31
 */
public interface BaseService<VO, Param> {

    VO get(Long id);

    PageInfo<VO> get(Param param);

    void save(Param param);

    void delete(Long id);
}
