package com.codeartist.component.core.sample.controller;

import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常接口测试
 *
 * @author 艾江南
 * @date 2022/7/22
 */
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
}
