package com.codeartist.component.core.sample.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 异常接口测试
 *
 * @author 艾江南
 * @date 2022/7/22
 */
@FeignClient(name = "sample", url = "http://localhost:8080", path = "/api/exception")
public interface ExceptionFeignService {

    @GetMapping("/client")
    String client();

    @GetMapping("/business")
    String business();

    @GetMapping("/server")
    String server();
}
