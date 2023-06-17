package com.codeartist.component.generator.sample.controller;

import com.codeartist.component.core.api.AbstractController;
import com.codeartist.component.generator.sample.entity.param.UserParam;
import com.codeartist.component.generator.sample.entity.vo.UserVO;
import com.codeartist.component.generator.sample.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基本信息 控制器
 *
 * @author CodeGenerator
 * @since 2023-06-17
 */
@Api(tags = "用户基本信息")
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController<UserVO, UserParam> {

    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }
}
