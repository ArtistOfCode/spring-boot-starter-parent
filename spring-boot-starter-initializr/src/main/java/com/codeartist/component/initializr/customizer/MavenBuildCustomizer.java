package com.codeartist.component.initializr.customizer;

import com.codeartist.component.initializr.contributor.CustomMavenBuild;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.metadata.InitializrConfiguration;
import io.spring.initializr.metadata.InitializrMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Maven构建定制
 *
 * @author J.N.AI
 * @date 2023/6/3
 */
public class MavenBuildCustomizer implements BuildCustomizer<CustomMavenBuild> {

    private final ProjectDescription description;
    private final InitializrMetadata metadata;

    public MavenBuildCustomizer(ProjectDescription description, InitializrMetadata metadata) {
        this.description = description;
        this.metadata = metadata;
    }

    @Override
    public void customize(CustomMavenBuild build) {
        // 解析Parent 依赖
        InitializrConfiguration.Env.Maven maven = this.metadata.getConfiguration().getEnv().getMaven();
        String springBootVersion = description.getPlatformVersion().toString();
        InitializrConfiguration.Env.Maven.ParentPom parentPom = maven.resolveParentPom(springBootVersion);

        build.settings()
                .parent(parentPom.getGroupId(), parentPom.getArtifactId(), parentPom.getVersion())
                .group(description.getGroupId())
                .artifact(description.getArtifactId())
                .version(description.getVersion())
                .name(description.getName())
                .description(description.getDescription())
        ;

        buildModules(build);

        buildApiMaven(build);

        buildWebMaven(build);
    }

    private static void buildModules(CustomMavenBuild build) {
        List<String> modules = new ArrayList<>(2);
        modules.add(build.apiArtifactId());
        modules.add(build.webArtifactId());
        build.setModules(modules);
    }

    private void buildApiMaven(CustomMavenBuild build) {
        CustomMavenBuild apiMavenBuild = new CustomMavenBuild();
        apiMavenBuild.settings()
                .parent(description.getGroupId(), description.getArtifactId(), description.getVersion())
                .artifact(build.apiArtifactId())
                .version(null);

        apiMavenBuild.dependencies().add("1", Dependency
                .withCoordinates("org.springframework.cloud", "spring-cloud-starter-openfeign")
                .scope(DependencyScope.PROVIDED_RUNTIME));

        apiMavenBuild.dependencies().add("2", Dependency
                .withCoordinates("org.springframework.boot", "spring-boot-starter-web")
                .scope(DependencyScope.PROVIDED_RUNTIME));

        apiMavenBuild.dependencies().add("3", Dependency
                .withCoordinates("org.projectlombok", "lombok")
                .scope(DependencyScope.PROVIDED_RUNTIME));

        build.setApiMavenBuild(apiMavenBuild);
    }

    private void buildWebMaven(CustomMavenBuild build) {
        CustomMavenBuild webMavenBuild = new CustomMavenBuild();
        webMavenBuild.settings()
                .parent(description.getGroupId(), description.getArtifactId(), description.getVersion())
                .artifact(build.webArtifactId())
                .version("1.0.0")
        ;
        build.setWebMavenBuild(webMavenBuild);
    }
}
