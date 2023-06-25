package com.codeartist.component.core.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 核心组件测试项目
 *
 * @author J.N.AI
 * @date 2023/6/25
 */
@EnableFeignClients("com.codeartist.component.core.sample.feign")
@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
