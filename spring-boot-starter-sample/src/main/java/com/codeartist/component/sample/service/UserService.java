package com.codeartist.component.sample.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codeartist.component.core.entity.PageInfo;
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
public class UserService {

    private final UserMapper userMapper;
    private final UserConverter userConverter;

    public PageInfo<UserVO> getPage(UserParam param) {
        IPage<User> page = userMapper.selectPage(param.page(), new QueryWrapper<>(userConverter.toDo(param)));
        return new PageInfo<>(page, userConverter::toVo);
    }
}
