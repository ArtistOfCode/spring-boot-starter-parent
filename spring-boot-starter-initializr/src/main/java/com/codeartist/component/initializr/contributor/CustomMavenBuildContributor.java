package com.codeartist.component.initializr.contributor;

import io.spring.initializr.generator.buildsystem.BuildWriter;
import io.spring.initializr.generator.io.IndentingWriter;
import io.spring.initializr.generator.io.IndentingWriterFactory;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author J.N.AI
 * @date 2023/6/5
 */
public class CustomMavenBuildContributor implements BuildWriter, ProjectContributor {

    private final CustomMavenBuild build;

    private final IndentingWriterFactory indentingWriterFactory;

    private final CustomMavenBuildWriter buildWriter;

    public CustomMavenBuildContributor(CustomMavenBuild build, IndentingWriterFactory indentingWriterFactory) {
        this.build = build;
        this.indentingWriterFactory = indentingWriterFactory;
        this.buildWriter = new CustomMavenBuildWriter();
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        Path pomFile = Files.createFile(projectRoot.resolve("pom.xml"));
        writeBuild(Files.newBufferedWriter(pomFile));
    }

    @Override
    public void writeBuild(Writer out) throws IOException {
        try (IndentingWriter writer = this.indentingWriterFactory.createIndentingWriter("maven", out)) {
            this.buildWriter.writeTo(writer, this.build);
        }
    }
}
