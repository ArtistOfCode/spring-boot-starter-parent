package com.codeartist.component.sample.controller;

import com.codeartist.component.sample.entity.GenericExample;
import com.codeartist.component.sample.entity.Example;
import com.codeartist.component.sample.service.feign.DemoFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * Restful接口测试
 *
 * @author 艾江南
 * @date 2022/7/22
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/rest")
public class RestApiController {

    private final DemoFeignService demoFeignService;

    @GetMapping("/longToStr")
    public Map<String, Long> longToStr() {
        return Collections.singletonMap("number", 13700001111L);
    }

    @GetMapping("/trace")
    public void trace() {
        log.info("This is a trace log.");
    }

    @GetMapping("/rpc/trace")
    public void rpc() {
        log.info("This is a trace log.");
        demoFeignService.trace();
    }

    @Async
    @GetMapping("/async")
    public void async() {
        log.info("This is async method.");
    }

    @PostMapping("/json")
    public void json(@RequestBody GenericExample<Example> param) {
        System.out.println(param);
    }
}
