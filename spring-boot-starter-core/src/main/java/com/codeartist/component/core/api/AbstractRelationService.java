package com.codeartist.component.core.api;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.codeartist.component.core.entity.PageParam;
import com.codeartist.component.core.entity.RelationParam;
import com.codeartist.component.core.entity.RelationVO;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 抽象关联服务类
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
@Getter
public abstract class AbstractRelationService<R_DO, DO, VO, Param extends PageParam> extends AbstractService<DO, VO, Param>
        implements RelationService {

    private final BaseMapper<DO> mapper;
    private final BaseConverter<DO, Param, VO> converter;
    private final RelationMapper<R_DO> relationMapper;

    /**
     * 一对多的字段
     */
    private SFunction<R_DO, Long> one;
    private SFunction<R_DO, Long> more;

    protected AbstractRelationService(BaseMapper<DO> mapper,
                                      RelationMapper<R_DO> relationMapper,
                                      BaseConverter<DO, Param, VO> converter) {
        super(mapper, converter);
        this.mapper = mapper;
        this.relationMapper = relationMapper;
        this.converter = converter;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        super.delete(id);
        relationMapper.delete(Wrappers.<R_DO>lambdaQuery().eq(more, id));
    }

    @Override
    public RelationVO getRelation(Long id) {
        List<R_DO> list = relationMapper.selectList(Wrappers.<R_DO>lambdaQuery().eq(one, id));
        Set<Long> ids = list.stream().map(more).collect(Collectors.toSet());
        RelationVO entity = new RelationVO();
        entity.setId(id);
        entity.setIds(ids);
        return entity;
    }

    @Override
    @Transactional
    public void saveRelation(RelationParam param) {
        relationMapper.delete(Wrappers.<R_DO>lambdaQuery().eq(one, param.getId()));
        if (!CollectionUtils.isEmpty(param.getIds())) {
            relationMapper.insertBatch(param);
        }
    }

    protected void setRelationColumn(SFunction<R_DO, Long> one, SFunction<R_DO, Long> more) {
        this.one = one;
        this.more = more;
    }
}
