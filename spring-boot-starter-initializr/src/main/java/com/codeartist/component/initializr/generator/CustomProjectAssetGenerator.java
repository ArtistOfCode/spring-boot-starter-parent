package com.codeartist.component.initializr.generator;

import com.codeartist.component.initializr.contributor.CustomMavenBuildContributor;
import io.spring.initializr.generator.project.ProjectAssetGenerator;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectDirectoryFactory;
import io.spring.initializr.generator.project.ProjectGenerationContext;
import io.spring.initializr.generator.spring.scm.git.GitIgnoreContributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author J.N.AI
 * @date 2023/6/5
 */
public class CustomProjectAssetGenerator implements ProjectAssetGenerator<Path> {

    @Override
    public Path generate(ProjectGenerationContext context) throws IOException {
        ProjectDescription description = context.getBean(ProjectDescription.class);
        Path projectRoot = resolveProjectDirectoryFactory(context).createProjectDirectory(description);
        Path projectDirectory = initializerProjectDirectory(projectRoot, description);

        context.getBean(CustomMavenBuildContributor.class).contribute(projectDirectory);
        context.getBean(GitIgnoreContributor.class).contribute(projectDirectory);

        return projectRoot;
    }

    private ProjectDirectoryFactory resolveProjectDirectoryFactory(ProjectGenerationContext context) {
        return context.getBean(ProjectDirectoryFactory.class);
    }


    private Path initializerProjectDirectory(Path rootDir, ProjectDescription description) throws IOException {
        Path projectDirectory = resolveProjectDirectory(rootDir, description);
        Files.createDirectories(projectDirectory);
        return projectDirectory;
    }

    private Path resolveProjectDirectory(Path rootDir, ProjectDescription description) {
        if (description.getBaseDirectory() != null) {
            return rootDir.resolve(description.getBaseDirectory());
        }
        return rootDir;
    }
}
