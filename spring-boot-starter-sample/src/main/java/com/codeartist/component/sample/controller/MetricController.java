package com.codeartist.component.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 指标接口
 *
 * @author 艾江南
 * @date 2022/11/30
 */
@RestController
@RequestMapping("/api/metric")
public class MetricController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public void get() {
        System.out.println(discoveryClient);
    }
}
