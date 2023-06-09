package com.codeartist.component.generator.sample.service;

import com.codeartist.component.core.api.AbstractService;
import com.codeartist.component.generator.sample.entity.User;
import com.codeartist.component.generator.sample.entity.converter.UserConverter;
import com.codeartist.component.generator.sample.entity.param.UserParam;
import com.codeartist.component.generator.sample.entity.vo.UserVO;
import com.codeartist.component.generator.sample.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户基本信息 服务实现类
 *
 * @author CodeGenerator
 * @since 2023-06-17
 */
@Service
public class UserService extends AbstractService<User, UserVO, UserParam> {

    private final UserMapper userMapper;
    private final UserConverter userConverter;

    public UserService(UserMapper userMapper, UserConverter userConverter) {
        super(userMapper, userConverter);
        this.userMapper = userMapper;
        this.userConverter = userConverter;
    }
}
