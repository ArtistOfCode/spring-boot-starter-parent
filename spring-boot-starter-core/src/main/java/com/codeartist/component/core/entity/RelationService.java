package com.codeartist.component.core.entity;

/**
 * 关联表操作接口
 *
 * @author 艾江南
 * @date 2023/4/23
 */
public interface RelationService {

    RelationVO getRelation(Long id);

    void saveRelation(RelationParam param);
}
