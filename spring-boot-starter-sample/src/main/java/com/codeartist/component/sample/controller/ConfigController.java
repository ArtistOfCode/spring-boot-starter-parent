package com.codeartist.component.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Consul配置中心测试
 *
 * @author 艾江南
 * @date 2022/11/8
 */
@Slf4j
@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Value("${consul.config:}")
    private String demoConfig;
    @Value("${consul.global.config:}")
    private String demoGlobalConfig;
    @Value("${consul.local.config:}")
    private String demoLocalConfig;
    @Value("${consul.local.global.config:}")
    private String demoGlobalLocalConfig;

    @GetMapping
    public String config() {
        log.info("demoConfig:{}, demoGlobalConfig:{}", demoConfig, demoGlobalConfig);
        log.info("demoLocalConfig:{}, demoGlobalLocalConfig:{}", demoLocalConfig, demoGlobalLocalConfig);
        return demoConfig;
    }
}
