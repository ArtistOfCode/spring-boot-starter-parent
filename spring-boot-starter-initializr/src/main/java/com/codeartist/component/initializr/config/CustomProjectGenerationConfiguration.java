package com.codeartist.component.initializr.config;

import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.springframework.context.annotation.Bean;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 自定义脚手架生成配置
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
@ProjectGenerationConfiguration
public class CustomProjectGenerationConfiguration {

    @Bean
    public ProjectContributor sample() {
        return projectRoot -> {
            Path file = Files.createFile(projectRoot.resolve("hello.java"));
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
                writer.println("Test");
            }
        };
    }
}
