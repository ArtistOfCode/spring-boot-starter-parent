package com.codeartist.component.core.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codeartist.component.core.entity.EntityEvent;
import com.codeartist.component.core.entity.PageInfo;
import com.codeartist.component.core.entity.PageParam;
import com.codeartist.component.core.entity.enums.GlobalConstants.EntityEventType;
import com.codeartist.component.core.util.SpringContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 抽象服务类
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractService<DO, VO, Param extends PageParam> implements BaseService<VO, Param> {

    private final BaseMapper<DO> mapper;
    private final BaseConverter<DO, Param, VO> converter;

    @Override
    public VO get(Long id) {
        DO entity = getMapper().selectById(id);
        return getConverter().toVo(entity);
    }

    @Override
    public PageInfo<VO> get(Param param) {
        DO entity = getConverter().toDo(param);
        IPage<DO> page = getMapper().selectPage(param.page(), new QueryWrapper<>(entity));
        return new PageInfo<>(page, getConverter()::toVo);
    }

    @Override
    @Transactional
    public void save(Param param) {
        SpringContext.validate(param);

        EntityEvent<DO> event = new EntityEvent<>(this);
        DO entity = getConverter().toDo(param);
        if (param.getId() != null) {
            getMapper().updateById(entity);
            event.setType(EntityEventType.UPDATE);
        } else {
            getMapper().insert(entity);
            event.setType(EntityEventType.SAVE);
        }
        event.setEntity(entity);

        SpringContext.publishEvent(event);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DO entity = getMapper().selectById(id);
        if (entity == null) {
            return;
        }

        getMapper().deleteById(id);
        SpringContext.publishEvent(new EntityEvent<>(this, EntityEventType.DELETE, entity));
    }
}
