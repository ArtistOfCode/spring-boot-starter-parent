package com.codeartist.component.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.query.SQLQuery;
import com.codeartist.component.generator.entity.GenerateProperties;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Properties;

import static java.lang.String.format;

/**
 * 生成工具类
 *
 * @author 艾江南
 * @date 2022/8/2
 */
public final class GenerateUtils {

    private GenerateUtils() {
    }

    public static void generate(GenerateProperties properties) {
        String projectPath = System.getProperty("user.dir");
        loadDatasource(properties);

        if (properties.getTablesPrefix() == null || properties.getTablesPrefix().length == 0) {
            properties.setTablesPrefix("t_");
        }

        if (StringUtils.isEmpty(properties.getPackageName())) {
            properties.setPackageName("com.idatafun");
        }

        FastAutoGenerator.create(new DataSourceConfig.Builder(properties.getUrl(), properties.getUsername(), properties.getPassword())
                        .dbQuery(properties.getDbQuery())
                        .databaseQueryClass(SQLQuery.class))
                .globalConfig(builder -> builder
                        .author("CodeGenerator")
                        .disableOpenDir()
                        .disableServiceInterface()
                        .dateType(DateType.TIME_PACK)
                        .outputDir(format("%s/src/main/java", projectPath))
                )
                .packageConfig(builder -> builder
                        .parent(properties.getPackageName())
                        .entity("entity")
                        .serviceImpl("service")
                        .pathInfo(Collections.singletonMap(OutputFile.xml,
                                format("%s/src/main/resources/%s/mapper", projectPath, parseDir(properties.getPackageName())))
                        )
                )
                .templateConfig(builder -> builder
                        .entity("tpl/entity.java")
                        .controller("tpl/controller.java")
                        .serviceImpl("tpl/serviceImpl.java")
                )
                .strategyConfig(builder -> builder
                        .addInclude(properties.getTables())
                        .addTablePrefix(properties.getTablesPrefix())
                        // Entity
                        .entityBuilder()
                        .disableSerialVersionUID()
                        .enableLombok()
                        .enableFileOverride()
                        // Controller
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableFileOverride()
                        // Service
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sService")
                        .enableFileOverride()
                        // Mapper
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableFileOverride())
                .injectionConfig(builder -> builder
                        // vo
                        .customFile(build -> build.fileName("VO.java")
                                .templatePath("tpl/entity_vo.java.ftl")
                                .packageName("entity/vo")
                                .enableFileOverride()
                                .build())
                        // param
                        .customFile(build -> build.fileName("Param.java")
                                .templatePath("tpl/entity_param.java.ftl")
                                .packageName("entity/param")
                                .enableFileOverride()
                                .build())
                        // converter
                        .customFile(build -> build.fileName("Converter.java")
                                .templatePath("tpl/entity_converter.java.ftl")
                                .packageName("entity/converter")
                                .enableFileOverride()
                                .build())
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    private static void loadDatasource(GenerateProperties properties) {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        Properties prop;
        try {
            ClassPathResource resource = new ClassPathResource("application-local.yaml");
            yaml.setResources(resource);
            prop = yaml.getObject();
        } catch (IllegalStateException e) {
            ClassPathResource resource = new ClassPathResource("bootstrap.yaml");
            yaml.setResources(resource);
            prop = yaml.getObject();
        }

        if (prop == null) {
            throw new NullPointerException("Properties is null.");
        }

        properties.setUrl(prop.getProperty("spring.datasource.url", properties.getUrl()));
        properties.setUsername(prop.getProperty("spring.datasource.username", "sa"));
        properties.setPassword(prop.getProperty("spring.datasource.password", ""));
    }

    private static String parseDir(String packageName) {
        return packageName.replaceAll("\\.", "/");
    }
}
