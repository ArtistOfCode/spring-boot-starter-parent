package com.codeartist.component.sample.entity.converter;

import com.codeartist.component.sample.entity.User;
import com.codeartist.component.sample.entity.param.UserParam;
import com.codeartist.component.sample.entity.vo.UserVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 用户基本信息 实体转换
 *
 * @author CodeGenerator
 * @since 2023-02-20
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    User toDo(UserParam param);

    UserVO toVo(User user);

    List<UserVO> toVo(List<User> users);
}
