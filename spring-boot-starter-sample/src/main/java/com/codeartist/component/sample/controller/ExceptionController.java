package com.codeartist.component.sample.controller;

import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;
import com.codeartist.component.sample.entity.param.UserParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 异常接口测试
 *
 * @author 艾江南
 * @date 2022/7/22
 */
@Validated
@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @GetMapping("/client")
    public String client() {
        throw new BadRequestException("请求异常");
    }

    @GetMapping("/business")
    public String business() {
        throw new BusinessException("业务异常");
    }

    @GetMapping("/server")
    public String server() {
        throw new RuntimeException("服务器异常");
    }

    @PostMapping("/args")
    public void args(@NotNull Long id) {
    }

    @PostMapping("/body-args")
    public void body(@Valid @RequestBody UserParam param) {
    }
}
