package com.codeartist.component.generator.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成组件测试
 *
 * @author J.N.AI
 * @date 2023/6/12
 */
@SpringBootApplication
@MapperScan("com.codeartist.component.generator.sample.mapper")
public class GeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
