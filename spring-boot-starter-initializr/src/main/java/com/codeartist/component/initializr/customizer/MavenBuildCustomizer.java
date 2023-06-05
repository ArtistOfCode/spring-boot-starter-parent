package com.codeartist.component.initializr.customizer;

import com.codeartist.component.initializr.contributor.CustomMavenBuild;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import org.springframework.core.Ordered;

/**
 * Maven构建定制
 *
 * @author J.N.AI
 * @date 2023/6/3
 */
public class MavenBuildCustomizer implements BuildCustomizer<CustomMavenBuild> {

    private final ProjectDescription description;

    public MavenBuildCustomizer(ProjectDescription description) {
        this.description = description;
    }

    @Override
    public void customize(CustomMavenBuild build) {
        build.settings().group(this.description.getGroupId()).artifact(this.description.getArtifactId())
                .version(this.description.getVersion());
        this.description.getRequestedDependencies()
                .forEach((id, dependency) -> build.dependencies().add(id, dependency));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
