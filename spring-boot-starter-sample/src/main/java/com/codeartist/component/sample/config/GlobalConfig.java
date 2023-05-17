package com.codeartist.component.sample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 全局配置
 *
 * @author 艾江南
 * @date 2021/6/7
 */
@SpringBootConfiguration
//@EnableLdapRepositories("com.codeartist.component.sample.repository")
@MapperScan("com.codeartist.component.sample.mapper")
@EnableFeignClients({"com.codeartist.component.sample.service.feign"})
public class GlobalConfig {
}
