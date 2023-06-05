package com.codeartist.component.initializr.config;

import com.codeartist.component.initializr.customizer.MavenBuildCustomizer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author J.N.AI
 * @date 2023/6/5
 */
@ProjectGenerationConfiguration
public class BuildProjectGenerationConfiguration {

    @Bean
    public MavenBuildCustomizer mavenBuildCustomizer(ProjectDescription description) {
        return new MavenBuildCustomizer(description);
    }
}
