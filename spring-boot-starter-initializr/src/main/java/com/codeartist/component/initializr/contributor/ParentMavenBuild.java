package com.codeartist.component.initializr.contributor;

import io.spring.initializr.generator.buildsystem.BuildItemResolver;
import io.spring.initializr.generator.buildsystem.maven.MavenBuild;

import java.util.List;

/**
 * 父Maven项目
 *
 * @author J.N.AI
 * @date 2023/6/2
 */
public class ParentMavenBuild extends MavenBuild {

    private List<String> modules;

    public ParentMavenBuild() {
    }

    public ParentMavenBuild(BuildItemResolver buildItemResolver) {
        super(buildItemResolver);
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }
}
