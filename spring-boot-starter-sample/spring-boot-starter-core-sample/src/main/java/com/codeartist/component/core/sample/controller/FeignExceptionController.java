package com.codeartist.component.core.sample.controller;

import com.codeartist.component.core.sample.feign.ExceptionFeignService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/feign")
public class FeignExceptionController {

    private final ExceptionFeignService exceptionFeignService;

    @GetMapping("/client")
    public String client() {
        exceptionFeignService.client();
        return "OK";
    }

    @GetMapping("/business")
    public String business() {
        exceptionFeignService.business();
        return "OK";
    }

    @GetMapping("/server")
    public String server() {
        exceptionFeignService.server();
        return "OK";
    }
}
