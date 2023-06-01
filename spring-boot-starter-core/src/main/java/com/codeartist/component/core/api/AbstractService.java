package com.codeartist.component.core.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codeartist.component.core.entity.PageInfo;
import com.codeartist.component.core.entity.PageParam;
import com.codeartist.component.core.util.SpringContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * 抽象服务类
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
public abstract class AbstractService<DO, VO, Param extends PageParam> implements BaseService<VO, Param> {

    protected abstract BaseMapper<DO> getMapper();

    protected abstract BaseConverter<DO, Param, VO> getConverter();

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

        DO entity = getConverter().toDo(param);
        if (param.getId() != null) {
            getMapper().updateById(entity);
        } else {
            getMapper().insert(entity);
        }
        SpringContext.publishEvent(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        DO entity = getMapper().selectById(id);
        if (entity == null) {
            return;
        }

        getMapper().deleteById(id);
        SpringContext.publishEvent(entity);
    }
}
