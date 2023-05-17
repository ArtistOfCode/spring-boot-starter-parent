package com.codeartist.component.autoconfigure.web;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Web项目加载配置
 *
 * @author 艾江南
 * @date 2022/12/2
 */
@SpringBootConfiguration
public class WebBootstrapAutoConfiguration {

    @Bean
    public CommonPropertySourceLocator commonPropertySourceLocator() {
        return new CommonPropertySourceLocator();
    }
}
