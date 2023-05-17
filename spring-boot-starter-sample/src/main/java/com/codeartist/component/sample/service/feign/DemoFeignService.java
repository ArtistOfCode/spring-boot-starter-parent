package com.codeartist.component.sample.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试Feign调用
 *
 * @author 艾江南
 * @date 2022/11/3
 */
@FeignClient(value = "pms", path = "/api/rest")
public interface DemoFeignService {

    @GetMapping("/trace")
    void trace();
}
