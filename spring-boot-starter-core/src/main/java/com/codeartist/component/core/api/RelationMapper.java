package com.codeartist.component.core.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeartist.component.core.entity.RelationParam;

/**
 * 关联表数据库操作
 *
 * @author 艾江南
 * @date 2023/6/28
 */
public interface RelationMapper<T> extends BaseMapper<T> {

    void insertBatch(RelationParam param);
}
