package com.codeartist.component.sample.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeartist.component.core.api.AbstractService;
import com.codeartist.component.core.api.BaseConverter;
import com.codeartist.component.sample.entity.User;
import com.codeartist.component.sample.entity.converter.UserConverter;
import com.codeartist.component.sample.entity.param.UserParam;
import com.codeartist.component.sample.entity.vo.UserVO;
import com.codeartist.component.sample.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户基本信息 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-02-20
 */
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
}
