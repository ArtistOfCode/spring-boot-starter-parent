package com.codeartist.component.sample.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeartist.component.core.api.AbstractService;
import com.codeartist.component.core.api.BaseConverter;
import com.codeartist.component.core.entity.EntityEvent;
import com.codeartist.component.core.util.JSON;
import com.codeartist.component.sample.entity.Example;
import com.codeartist.component.sample.entity.User;
import com.codeartist.component.sample.entity.converter.UserConverter;
import com.codeartist.component.sample.entity.param.UserParam;
import com.codeartist.component.sample.entity.vo.UserVO;
import com.codeartist.component.sample.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 用户基本信息 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-02-20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<User, UserVO, UserParam> {

    private final UserMapper userMapper;
    private final UserConverter userConverter;

    @Override
    protected BaseMapper<User> getMapper() {
        return this.userMapper;
    }

    @Override
    protected BaseConverter<User, UserParam, UserVO> getConverter() {
        return this.userConverter;
    }

    @EventListener
    public void userListener(EntityEvent<User> entity) {
        log.info("User change listener: {}", JSON.toJSONString(entity));
    }

    @EventListener
    public void exampleListener(EntityEvent<Example> entity) {
        log.info("Example change listener: {}", JSON.toJSONString(entity));
    }
}
