package com.codeartist.component.initializr.contributor;

import io.spring.initializr.generator.buildsystem.maven.MavenBuildWriter;
import io.spring.initializr.generator.io.IndentingWriter;

/**
 * 父Maven项目
 *
 * @author J.N.AI
 * @date 2023/6/2
 */
public class ParentMavenBuildWriter extends MavenBuildWriter {

    public void writeTo(IndentingWriter writer, ParentMavenBuild build) {
        super.writeTo(writer, build);
    }
}
