package com.codeartist.component.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 组件示例项目
 *
 * @author 艾江南
 * @date 2021/5/9
 */
@SpringBootApplication(proxyBeanMethods = false)
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
