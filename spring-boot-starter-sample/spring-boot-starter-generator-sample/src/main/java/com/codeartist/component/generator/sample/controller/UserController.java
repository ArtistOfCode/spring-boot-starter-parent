package com.codeartist.component.generator.sample.controller;

import com.codeartist.component.core.api.AbstractController;
import com.codeartist.component.core.api.BaseService;
import com.codeartist.component.generator.sample.entity.param.UserParam;
import com.codeartist.component.generator.sample.entity.vo.UserVO;
import com.codeartist.component.generator.sample.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基本信息 控制器
 *
 * @author CodeGenerator
 * @since 2023-06-12
 */
@Api(tags = "用户基本信息")
@Validated
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController extends AbstractController<UserVO, UserParam> {

    private final UserService userService;

    @Override
    protected BaseService<UserVO, UserParam> getService() {
        return this.userService;
    }
}
