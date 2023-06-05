/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeartist.component.initializr.config;

import com.codeartist.component.initializr.contributor.CustomMavenBuild;
import com.codeartist.component.initializr.contributor.code.CustomPackageInfoContributor;
import com.codeartist.component.initializr.contributor.code.CustomTestSourceContributor;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.io.IndentingWriterFactory;
import io.spring.initializr.generator.language.Annotation;
import io.spring.initializr.generator.language.java.*;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.code.TestApplicationTypeCustomizer;
import io.spring.initializr.generator.spring.code.TestSourceCodeCustomizer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
@ConditionalOnLanguage(JavaLanguage.ID)
public class JavaProjectGenerationConfiguration {

    private final ProjectDescription description;

    private final IndentingWriterFactory indentingWriterFactory;

    public JavaProjectGenerationConfiguration(ProjectDescription description,
                                              IndentingWriterFactory indentingWriterFactory) {
        this.description = description;
        this.indentingWriterFactory = indentingWriterFactory;
    }

    @Bean
    public TestApplicationTypeCustomizer<JavaTypeDeclaration> junitJupiterTestMethodContributor() {
        return (typeDeclaration) -> {
            typeDeclaration.extend("com.codeartist.component.core.support.test.AbstractSpringRunnerTests");
            JavaMethodDeclaration method = JavaMethodDeclaration.method("test").returning("void").body();
            method.annotate(Annotation.name("org.junit.jupiter.api.Test"));
            typeDeclaration.addMethodDeclaration(method);
        };
    }

    @Bean
    public CustomTestSourceContributor<JavaTypeDeclaration, JavaCompilationUnit, JavaSourceCode> customTestSourceContributor(
            ObjectProvider<TestApplicationTypeCustomizer<?>> testApplicationTypeCustomizers,
            ObjectProvider<TestSourceCodeCustomizer<?, ?, ?>> testSourceCodeCustomizers) {
        return new CustomTestSourceContributor<>(this.description, JavaSourceCode::new,
                new JavaSourceCodeWriter(this.indentingWriterFactory), testApplicationTypeCustomizers,
                testSourceCodeCustomizers);
    }

    @Bean
    public CustomPackageInfoContributor customPackageInfoContributor(ProjectDescription description, CustomMavenBuild build) {
        return new CustomPackageInfoContributor(description, build);
    }
}
