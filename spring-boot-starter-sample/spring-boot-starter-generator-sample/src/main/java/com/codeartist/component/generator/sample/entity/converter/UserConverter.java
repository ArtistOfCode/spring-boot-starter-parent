package com.codeartist.component.generator.sample.entity.converter;

import com.codeartist.component.core.api.BaseConverter;
import com.codeartist.component.generator.sample.entity.User;
import com.codeartist.component.generator.sample.entity.param.UserParam;
import com.codeartist.component.generator.sample.entity.vo.UserVO;
import org.mapstruct.Mapper;

/**
 * 用户基本信息 实体转换
 *
 * @author CodeGenerator
 * @since 2023-06-12
 */
@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter<User, UserParam, UserVO> {
}
