package com.codeartist.component.initializr.web.config;

import com.codeartist.component.initializr.controller.CustomProjectGenerationController;
import com.codeartist.component.initializr.generator.CustomProjectAssetGenerator;
import io.spring.initializr.generator.project.ProjectAssetGenerator;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.web.project.*;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

/**
 * @author J.N.AI
 * @date 2023/6/5
 */
@SpringBootConfiguration
public class WebConfiguration {

    @Bean
    public CustomProjectGenerationController customProjectGenerationController(
            InitializrMetadataProvider metadataProvider,
            ObjectProvider<ProjectRequestPlatformVersionTransformer> platformVersionTransformer,
            ApplicationContext applicationContext) {
        ProjectGenerationInvoker<ProjectRequest> projectGenerationInvoker = new ProjectGenerationInvoker<ProjectRequest>(
                applicationContext, new DefaultProjectRequestToDescriptionConverter(platformVersionTransformer
                .getIfAvailable(DefaultProjectRequestPlatformVersionTransformer::new))) {
            @Override
            protected ProjectAssetGenerator<Path> getProjectAssetGenerator(ProjectDescription description) {
                return new CustomProjectAssetGenerator();
            }
        };
        return new CustomProjectGenerationController(metadataProvider, projectGenerationInvoker);
    }
}
