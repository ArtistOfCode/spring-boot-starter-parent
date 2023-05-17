package com.codeartist.component.core.entity;

/**
 * 基础服务类
 *
 * @author 艾江南
 * @since 2022-08-31
 */
public interface BaseService<VO, P> {

    VO get(Long id);

    PageInfo<VO> get(P param);

    void save(P param);

    default void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
